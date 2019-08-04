package space.zero.september.common.core.returncode;


/**
 * @author : penggs
 * @program : september
 * @description : http响应码
 * @create : 2019-07-18 20:34
 */
public enum HttpCode {
    /**
     * 正常 20000000000
     */
    OK(20000000000L, "正常"),
    /**
     * 创建资源成功 20100000000
     */
    CREATED(20100000000L, "创建资源成功"),
    /**
     * 请求已接受，但处理过程较长，不能马上返回结果 20200000000
     */
    ACCEPTED(20200000000L, "请求已接受"),
    /**
     * 部分内容（成功处理了部分内容，另外部分处理失败）
     */
    PARTIAL_CONTENT(20600000000L, "部分内容"),
    /**
     * 没有发生任何修改 30400000000
     */
    NOT_MODIFIED(30400000000L, "没有发生任何修改"),
    /**
     * 未授权（未登录）40100000000
     */
    UNAUTHORIZED(40100000000L, "未授权"),
    /**
     * 拒绝访问（已登录）40300000000
     */
    FORBIDDEN(40300000000L, "拒绝访问"),
    /**
     * 不存在所请求资源 40400000000
     */
    NOT_FOUND(40400000000L, "不存在所请求资源"),
    /**
     * 请求未接受，参数校验未通过或其他业务类型错误 40600000000
     */
    NOT_ACCEPTABLE(40600000000L, "请求未接受"),
    /**
     * 请求资源发生冲突 40900000000
     */
    CONFLICT(40900000000L, "请求资源发生冲突"),
    /**
     * 服务器非业务类型错误 50000000000
     */
    INTERNAL_ERROR(50000000000L, "服务器错误"),
    /**
     * 服务不可用 50300000000
     */
    SERVICE_UNAVAILABLE(50300000000L, "服务不可用"),
    ;

    private long code;
    private String msg;

    public String msg() {
        return msg;
    }

    public long code() {
        return code;
    }

    HttpCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static HttpCode getEnum(long code){
        for (HttpCode bc: HttpCode.values()){
            if (bc.code() == code){
                return bc;
            }
        }
        return null;
    }
}