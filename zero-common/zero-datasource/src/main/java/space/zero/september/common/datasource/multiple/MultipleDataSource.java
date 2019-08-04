package space.zero.september.common.datasource.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author : penggs
 * @program : september
 * @description : 多数据源
 * @create : 2019-07-16 19:56
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}