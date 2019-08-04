package space.zero.september.common.datasource.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import space.zero.september.common.datasource.annotation.DynamicDataSource;
import space.zero.september.common.datasource.multiple.DataSourceContextHolder;

/**
 * @author : penggs
 * @program : september
 * @description : 动态数据源切面
 * @create : 2019-07-16 20:07
 */
@Component
@Aspect
@Order(-1)
public class DynamicDataSourceAspect {
    Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("@within(space.zero.september.common.datasource.annotation.DynamicDataSource) || @annotation(space.zero.september.common.datasource.annotation.DynamicDataSource)")
    public void pointCut(){}

    @Before("pointCut() && @annotation(dynamicDataSource)")
    public void doBefore(DynamicDataSource dynamicDataSource){
        DataSourceContextHolder.setDataSource(dynamicDataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter() {
        DataSourceContextHolder.clear();
    }
}