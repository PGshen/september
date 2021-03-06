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
    P201(201, "文件不存在"),
    P202(202, "流操作异常"),
    P203(203, "文件格式校验失败"),
    P204(204, "文件内容校验失败"),
    P205(205, "文件大小超出限制"),

    /**
     * 数据类
     */
    P301(301, "数据不存在"),
    P302(302, "删除失败"),
    P303(303, "未更新"),
    P304(304, "有关联数据，不可删除"),
    P305(305, "数据深克隆错误"),
    P306(306, "数据已存在"),
    P307(307, "参数解析错误"),
    P308(308, "参数校验失败"),
    P309(309, "唯一性校验失败"),
    P310(310, "部分记录失败"),
    P311(311, "数据更新失败"),
    P312(312, "数据写入失败"),
    P313(313, "数据删除失败"),

    /**
     * 网络类
     */
    P401(401, "连接超时"),
    P402(402, "发送失败"),

    /**
     * 认证授权相关
     */
    P501(501, "用户名或密码错误"),
    P502(502, "令牌错误"),
    P503(503, "登录认证失败"),
    P504(504, "签名过期"),
    P505(505, "请求参数错误"),
    P506(506, "认证客户端信息异常"),
    P507(507, "拒绝访问"),
    P508(508, "未授权"),
    P509(509, "无效客户端"),
    P510(510, "未授权客户端"),
    P511(511, "无效的scope"),
    P512(512, "无效的token"),
    P513(513, "无效的请求"),
    P514(514, "重定向URL不匹配"),
    P515(515, "不支持的认证类型"),
    P516(516, "不支持的响应类型"),
    P517(517, "未知认证异常"),
    P518(518, "验证码错误"),
    P519(519, "账号被锁定，请联系管理员！"),
    P520(520, "操作过于频繁，请稍后再试！"),
    P521(521, "账号被禁用，请联系管理员！"),
    P522(522, "账号已过期，请联系管理员！"),

    /**
     * 服务类
     */
    P601(601, "服务不可用"),
    P602(602, "未找到对应方法"),

    /**
     * 批量/异步任务类
     */
    P701(701, "未找到任务"),
    P702(702, "任务调用失败")
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