package space.zero.september.common.datasource.enums;

/**
 * @author : penggs
 * @program : september
 * @description : 数据源枚举
 * @create : 2019-07-16 19:55
 */
public enum DataSourceEnum {

    /**
     * 数据源名称
     */
    DB("db"),
    DW("dw"),
    ONLINE("online");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}