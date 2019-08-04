package space.zero.september.common.datasource.annotation;

import space.zero.september.common.datasource.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * @author : penggs
 * @program : september
 * @description : 动态数据源切换注解
 * @create : 2019-07-16 19:54
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {
    DataSourceEnum value() default DataSourceEnum.DB;
}