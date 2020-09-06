package space.zero.september.common.security.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import space.zero.september.common.security.config.ResourceServer3AutoConfiguration;
import space.zero.september.common.security.config.SecurityBeanDefinitionRegistrar;

import java.lang.annotation.*;

/**
 * @author : penggs
 * @program : september
 * @description : 资源服务器注解
 * @create : 2019-09-03 17:49
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ResourceServer3AutoConfiguration.class, SecurityBeanDefinitionRegistrar.class})
public @interface EnableResourceServer3 {
}