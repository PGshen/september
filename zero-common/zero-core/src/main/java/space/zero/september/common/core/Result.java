package space.zero.september.common.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import space.zero.september.common.core.returncode.*;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * @author : penggs
 * @program : september
 * @description : 结果封装类
 * @create : 2019-07-18 19:22
 */
@ApiModel
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5679797130867189888L;/**
     * 自定义状态码取模，获取业务错误代码
     */
    private static final long MODULAR = 100000;

    /**
     * 返回码
     * http状态码(3) + 微服务(2) + 错误类型(1) + 错误来源(2) + 具体错误(3)
     */
    @ApiModelProperty(value = "自定义返回码", name = "code", example = "20001000000")
    private Long code;
    @ApiModelProperty(value = "响应提示消息", name = "message", example = "SUCCESS")
    private String message;
    @ApiModelProperty(value = "响应结果", name = "data")
    private T data;

    public Long getCode() {
        return code;
    }

    /**
     * 设置状态码, 当提示消息未设置时将自动设置，反之不会自动设置，若当前result之前已设置过其他提示消息且与当前状态码不符时，请重新手动设置
     * @param code 返回码
     * @return result
     */
    public Result<T> setCode(Long code){
        this.code = code;
        if (getMessage() == null || "".equals(getMessage())){
            String bpMsg = "未知";
            // 程序/业务编号
            long tCode = code % (MODULAR*10) /100000 * 100000;
            long bpCode = (code % MODULAR) / 1000 * 1000;
            if (tCode == TypeCode.PROGRAM.code()){
                ProgramCode programCode = ProgramCode.getEnum(bpCode);
                if (programCode != null){
                    bpMsg = programCode.msg();
                }
            } else if (tCode == TypeCode.BUSINESS.code() || tCode == TypeCode.NORMAL.code()){
                // 类型为NORMAL时，后接业务类代码
                BusinessCode businessCode = BusinessCode.getEnum(bpCode);
                if (businessCode != null){
                    bpMsg = businessCode.msg();
                }
            }
            // 错误编号
            String errorMsg = "未知";
            long eCode = code % (MODULAR/100);
            ErrorCode errorCode = ErrorCode.getEnum(eCode);
            if (errorCode != null) {
                errorMsg = errorCode.msg();
            }
            this.message = bpMsg + "-" + errorMsg;
        }
        return this;
    }

    public Result<T> setCode(HttpCode httpCode, ServiceCode serviceCode, TypeCode typeCode, BusinessCode businessCode, ErrorCode errorCode){
        long code = httpCode.code() + serviceCode.code() + typeCode.code() + businessCode.code() + errorCode.code();
        return this.setCode(code);
    }

    public Result<T> setCode(HttpCode httpCode, ServiceCode serviceCode, TypeCode typeCode, ProgramCode programCode, ErrorCode errorCode){
        long code = httpCode.code() + serviceCode.code() + typeCode.code() + programCode.code() + errorCode.code();
        return this.setCode(code);
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}