package space.zero.september.common.core.returncode;

/**
 * @author : penggs
 * @program : september
 * @description : 业务类错误
 * @create : 2019-07-18 20:43
 */
public enum  BusinessCode {
    /**
     * 正常 00000
     */
    NORMAL(0L, "正常"),
    /**
     * 异常 01000
     */
    ABNORMAL(1000L, "异常"),
    /**
     * api接口 02000
     */
    API(2000L, "api接口"),
    /**
     * 外部客户端 03000
     */
    CLIENT_APP(3000L, "外部客户端"),
    /**
     * 用户 04000
     */
    USER(4000L, "用户"),
    /**
     * 角色 05000
     */
    ROLE(5000L, "角色"),
    /**
     * 菜单 06000
     */
    MENU(6000L, "菜单"),
    /**
     * 租户 07000
     */
    TENANT(7000L, "租户"),
    /**
     * 组织机构 08000
     */
    ORGANIZATION(8000L, "组织机构"),
    ;

    private long code;
    private String msg;

    public String msg() {
        return msg;
    }

    public long code() {
        return code;
    }

    BusinessCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BusinessCode getEnum(long code){
        for (BusinessCode bc: BusinessCode.values()){
            if (bc.code() == code){
                return bc;
            }
        }
        return null;
    }
}