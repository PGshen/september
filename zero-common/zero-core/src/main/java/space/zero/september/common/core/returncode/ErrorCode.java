package space.zero.september.common.core.returncode;

/**
 * @author : penggs
 * @program : september
 * @description : 具体错误码
 * @create : 2019-07-18 20:48
 */
public enum  ErrorCode {
    /**
     * 成功 0开头
     */
    P000(0, "成功"),
    /**
     * 系统错误 1开头
     */
    P101(1, "程序异常,请联系系统管理员"),

    /**
     * 文件类错误 2开头
     */
    P201(101, "文件不存在"),

    /**
     * 数据类
     */
    P301(301, "数据不存在"),
    P302(302, "删除失败"),
    P303(303, "未更新"),
    P304(304, "有关联数据，不可删除"),
    P305(305, "数据深克隆错误"),
    P306(306, "数据已存在")
    ;
    private long code;
    private String msg;

    public String msg() {
        return msg;
    }

    public long code() {
        return code;
    }

    ErrorCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCode getEnum(long code){
        for (ErrorCode bc: ErrorCode.values()){
            if (bc.code() == code){
                return bc;
            }
        }
        return null;
    }
}