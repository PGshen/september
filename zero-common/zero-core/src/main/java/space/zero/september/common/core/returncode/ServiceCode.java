package space.zero.september.common.core.returncode;

/**
 * @author : penggs
 * @program : september
 * @description : 微服务编码
 * @create : 2019-07-18 20:36
 */
public enum ServiceCode {
    /**
     * 预留 00000000
     */
    RESERVED(0L, "预留"),
    /**
     * 注册中心 01000000
     */
    REGISTRATION(1000000L, "注册中心"),
    /**
     * 服务网关 02000000
     */
    GATEWAY(2000000L, "服务网关"),
    /**
     * 认证中心 03000000
     */
    AUTHENTICATION(3000000L, "认证中心"),
    /**
     * 服务监控 04000000
     */
    MONITOR(4000000L, "服务监控"),
    /**
     * 系统管理 05000000
     */
    ADMIN(5000000L, "系统管理"),

    ;

    private long code;
    private String msg;

    public String msg() {
        return msg;
    }

    public long code() {
        return code;
    }

    ServiceCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ServiceCode getEnum(long code){
        for (ServiceCode bc: ServiceCode.values()){
            if (bc.code() == code){
                return bc;
            }
        }
        return null;
    }
}