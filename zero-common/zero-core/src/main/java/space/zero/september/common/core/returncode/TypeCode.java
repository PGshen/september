package space.zero.september.common.core.returncode;

/**
 * @author : penggs
 * @program : september
 * @description : 错误类型
 * @create : 2019-07-18 20:39
 */
public enum TypeCode {
    /**
     * 正常 000000
     */
    NORMAL(0L, "正常"),
    /**
     * 程序错误 100000
     */
    PROGRAM(100000L, "程序"),
    /**
     * 实体/业务类错误 200000
     */
    BUSINESS(200000L, "业务"),

    ;

    private long code;
    private String msg;

    public String msg() {
        return msg;
    }

    public long code() {
        return code;
    }

    TypeCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static TypeCode getEnum(long code){
        for (TypeCode bc: TypeCode.values()){
            if (bc.code() == code){
                return bc;
            }
        }
        return null;
    }
}