package space.zero.september.common.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.GlobalConfig;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import space.zero.september.common.datasource.enums.DataSourceEnum;
import space.zero.september.common.datasource.exception.DatasourceConfigException;
import space.zero.september.common.datasource.handler.CustomMetaObjectHandler;
import space.zero.september.common.datasource.multiple.MultipleDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : mybatis plus配置
 * @create : 2019-07-16 20:03
 */
public class MyBatisPlusConfiguration {

    @Resource
    private ConfigurableApplicationContext configurableApplicationContext;

    /**
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    //@Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    /**
     * SQL执行效率插件
     */
    @Bean
//    @Profile({"dev"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    @Bean(name = "db01")
    @ConditionalOnProperty(prefix = "september.datasource.mysql.db01", name = {"url", "username", "password"})
    @ConfigurationProperties(prefix = "september.datasource.mysql.db01" )
    public DataSource db01() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db02")
    @ConditionalOnProperty(prefix = "september.datasource.oracle.db02", name = {"url", "username", "password"})
    @ConfigurationProperties(prefix = "september.datasource.oracle.db02" )
    public DataSource db02() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource() throws DatasourceConfigException {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        DataSource defaultDataSource = null;
        DataSource tempDataSource = null;
        Map< Object, Object > targetDataSources = new HashMap<>();
        // 遍历数据源枚举
        for (DataSourceEnum dse: DataSourceEnum.values()){
            if (configurableApplicationContext.containsLocalBean(dse.getValue())){
                tempDataSource = configurableApplicationContext.getBean(dse.getValue(), DataSource.class);
                targetDataSources.put(dse.getValue(), tempDataSource);
                // DataSourceEnum的第一个设置为默认数据源
                if (defaultDataSource == null){
                    defaultDataSource = tempDataSource;
                }
            }
        }
        // 没有配置数据源
        if (defaultDataSource == null || targetDataSources.size() < 1){
            throw new DatasourceConfigException();
        }
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源,配置的第一个为默认数据源
        multipleDataSource.setDefaultTargetDataSource(defaultDataSource);
        return multipleDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource());
        /**
         * mapper 扫描路径
         */
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*Mapper.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                //添加分页功能
                paginationInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfiguration());

        return sqlSessionFactory.getObject();
    }

    //@Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("1");
        conf.setLogicNotDeleteValue("0");
        conf.setIdType(0);
        conf.setMetaObjectHandler(new CustomMetaObjectHandler());
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        return conf;
    }
}