package space.zero.september.common.datasource.enums;

/**
 * @author : penggs
 * @program : september
 * @description : 数据源枚举
 * @create : 2019-07-16 19:55
 */
public enum DataSourceEnum {

    /**
     * 数据源名称,实际使用时应替换成容易辨别的名称
     */
    DB01("db01"),
    DB02("db02");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}