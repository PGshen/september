package space.zero.september.common.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.Duration;

/**
 * @author : penggs
 * @program : september
 * @description : redis配置,非集群
 * @create : 2019-08-11 15:23
 */
public class RedisConfiguration<K, V> {
    @Value("${redis.cache.expiration:360000}")
    private Long expiration;

    @Bean(name = "genericPoolConfig")
    @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    @Scope(value = "prototype")
    public GenericObjectPoolConfig redisPool(){
        return new GenericObjectPoolConfig();
    }

    /**
     * redis的配置
     *
     * @return org.springframework.data.redis.connection.RedisStandaloneConfiguration
     * @author penggs
     * @date 2019-04-28
     */
    @Bean(name = "redisForFirst")
    @ConditionalOnProperty(prefix = "spring.redis.redis-first", name = {"hostName", "port", "password"})
    @ConfigurationProperties(prefix = "spring.redis.redis-first")
    public RedisStandaloneConfiguration redisForFirst() {
        return new RedisStandaloneConfiguration();
    }

    /**
     * 多redis源
     */
    @Bean(name = "redisForSecond")
    @ConditionalOnProperty(prefix = "spring.redis.redis-second", name = {"hostName", "port", "password"})
    @ConfigurationProperties(prefix = "spring.redis.redis-second")
    public RedisStandaloneConfiguration redisForSecond() {
        return new RedisStandaloneConfiguration();
    }

    /**
     * 连接工厂
     *
     * @param config 连接池
     * @param redisForFirst 配置
     * @return org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
     * @author penggs
     * @date 2019-04-28
     */
    @Bean(name = "factoryForFirst")
    @ConditionalOnBean(name = "redisForFirst")
    @Primary
    public LettuceConnectionFactory factoryForCache(@Qualifier("genericPoolConfig") GenericObjectPoolConfig config, @Qualifier("redisForFirst") RedisStandaloneConfiguration redisForFirst) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(config).commandTimeout(Duration.ofMillis(config.getMaxWaitMillis())).build();
        return new LettuceConnectionFactory(redisForFirst, clientConfiguration);
    }

    @ConditionalOnBean(name = "redisForSecond")
    @Bean(name = "factoryForSecond")
    public LettuceConnectionFactory factoryForSecond(@Qualifier("genericPoolConfig") GenericObjectPoolConfig config, @Qualifier("redisForSecond") RedisStandaloneConfiguration redisForSecond){
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(config).commandTimeout(Duration.ofMillis(config.getMaxWaitMillis())).build();
        return new LettuceConnectionFactory(redisForSecond, clientConfiguration);
    }

    /**
     * redis客户端
     *
     * @param factoryForFirst 工厂
     * @return org.springframework.data.redis.core.RedisTemplate
     * @author penggs
     * @date 2019-04-28
     */
    @Primary
    @ConditionalOnBean(name = "factoryForFirst")
    @Bean(name = "firstRedisTemplate")
    public RedisTemplate<K, V> firstRedisTemplate(@Qualifier("factoryForFirst") LettuceConnectionFactory factoryForFirst){
        RedisTemplate<K, V> redisTemplate = this.getRedisTemplate();
        redisTemplate.setConnectionFactory(factoryForFirst);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @ConditionalOnBean(name = "factoryForSecond")
    @Bean(name = "secondRedisTemplate")
    public RedisTemplate<K, V> secondRedisTemplate(@Qualifier("factoryForSecond") LettuceConnectionFactory factoryForSecond){
        RedisTemplate<K, V> redisTemplate = this.getRedisTemplate();
        redisTemplate.setConnectionFactory(factoryForSecond);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean("firstCacheManager")
    @Primary
    public CacheManager firstCacheManager(@Qualifier("factoryForFirst") LettuceConnectionFactory factoryForFrist){
        RedisSerializationContext.SerializationPair<String> pairKey = RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
        RedisSerializationContext.SerializationPair<Object> pairVal = RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(expiration))
                .serializeKeysWith(pairKey)
                .serializeValuesWith(pairVal);
        RedisCacheManager cacheManager = RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factoryForFrist))
                .cacheDefaults(cacheConfiguration)
                .build();
        return cacheManager;
    }

    /**
     * 获取redis客户端
     *
     * @return org.springframework.data.redis.core.RedisTemplate
     * @author penggs
     * @date 2019-04-28
     */
    private RedisTemplate<K, V> getRedisTemplate(){
        RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    //配置事务管理器,以支持redis事务
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }
}