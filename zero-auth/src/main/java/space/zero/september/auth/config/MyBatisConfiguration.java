package space.zero.september.auth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import space.zero.september.common.datasource.config.MyBatisPlusConfiguration;

/**
 * @author : penggs
 * @program : september
 * @description : mybatis配置类
 * @create : 2019-07-17 13:39
 */
@Configuration
@MapperScan("space.zero.september.auth.*")
public class MyBatisConfiguration extends MyBatisPlusConfiguration {

}