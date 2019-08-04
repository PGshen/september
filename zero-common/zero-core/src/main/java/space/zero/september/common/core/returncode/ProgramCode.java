package space.zero.september.common.core.returncode;

/**
 * @author : penggs
 * @program : september
 * @description : 程序类错误
 * @create : 2019-07-18 20:42
 */
public enum  ProgramCode {
    /**
     * 正常 00000
     */
    NORMAL(0L, "正常"),
    /**
     * 异常 01000
     */
    ABNORMAL(1000L, "异常"),
    /**
     * 数据库 02000
     */
    DB(2000L, "数据库"),
    /**
     * 队列 03000
     */
    QUEUE(3000L, "队列"),
    /**
     * 接口 04000
     */
    INTERFACE(4000L, "接口"),
    ;

    private long code;
    private String msg;

    public String msg() {
        return msg;
    }

    public long code() {
        return code;
    }

    ProgramCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ProgramCode getEnum(long code){
        for (ProgramCode bc: ProgramCode.values()){
            if (bc.code() == code){
                return bc;
            }
        }
        return null;
    }
}