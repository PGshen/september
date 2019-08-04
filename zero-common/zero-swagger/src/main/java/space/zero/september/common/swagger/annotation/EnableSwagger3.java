package space.zero.september.common.swagger.annotation;

import org.springframework.context.annotation.Import;
import space.zero.september.common.swagger.config.SwaggerAutoConfiguration;

import java.lang.annotation.*;

/**
 * @author : penggs
 * @program : september
 * @description : Swagger注解
 * @create : 2019-07-19 18:48
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableSwagger3 {
}