package space.zero.september.common.core.returncode;

import space.zero.september.common.core.Result;

/**
 * @author : penggs
 * @program : september
 * @description : 结果封装
 * @create : 2019-07-18 20:46
 */
public class ResultGen<T> {

    /**
     * 成功，必须为业务类代码
     */
    public Result<T> success(ServiceCode serviceCode, BusinessCode businessCode){
        long code = HttpCode.OK.code() + serviceCode.code() + TypeCode.NORMAL.code() + businessCode.code() + ErrorCode.P000.code();
        return new Result<T>().setCode(code).setMessage(businessCode.msg() + "-" + ErrorCode.P000.msg());
    }

    public Result<T> success(ServiceCode serviceCode, BusinessCode businessCode, T data){
        Long code = HttpCode.OK.code() + serviceCode.code() + TypeCode.NORMAL.code() + businessCode.code() + ErrorCode.P000.code();
        return new Result<T>().setCode(code).setData(data).setMessage(businessCode.msg() + "-" + ErrorCode.P000.msg());
    }

    /**
     * 其他错误，指定状态码和微服务编码
     */
    public Result<T> fail(HttpCode httpCode, ServiceCode serviceCode, BusinessCode businessCode, ErrorCode errorCode){
        Long code = httpCode.code() + serviceCode.code() + TypeCode.BUSINESS.code() + businessCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setMessage(businessCode.msg() + "-" + errorCode.msg());
    }

    public Result<T> fail(HttpCode httpCode, ServiceCode serviceCode, ProgramCode programCode, ErrorCode errorCode){
        Long code = httpCode.code() + serviceCode.code() + TypeCode.PROGRAM.code() + programCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setMessage(programCode.msg() + "-" + errorCode.msg());
    }

    public Result<T> fail(HttpCode httpCode, ServiceCode serviceCode, BusinessCode businessCode, ErrorCode errorCode, T data){
        Long code = httpCode.code() + serviceCode.code() + TypeCode.BUSINESS.code() + businessCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setData(data).setMessage(businessCode.msg() + "-" + errorCode.msg());
    }

    public Result<T> fail(HttpCode httpCode, ServiceCode serviceCode, ProgramCode programCode, ErrorCode errorCode, T data){
        Long code = httpCode.code() + serviceCode.code() + TypeCode.PROGRAM.code() + programCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setData(data).setMessage(programCode.msg() + "-" + errorCode.msg());
    }

    public static boolean isOk(Long returnCode){
        return String.valueOf(returnCode).startsWith("200");
    }

    public static boolean is206(Long returnCode){
        return String.valueOf(returnCode).startsWith("206");
    }
}