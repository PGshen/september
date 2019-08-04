package space.zero.september.admin.util;

import org.springframework.stereotype.Component;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.returncode.*;

/**
 * @author : penggs
 * @program : september
 * @description : 结果封装工具类
 * @create : 2019-07-19 13:33
 */
@Component
public class ResultGen<T> {

    /**
     * 成功，必须为业务类代码
     */
    public Result<T> success(BusinessCode businessCode){
        Long code = HttpCode.OK.code() + ServiceCode.ADMIN.code() + TypeCode.NORMAL.code() + businessCode.code() + ErrorCode.P000.code();
        return new Result<T>().setCode(code);
    }

    public Result<T> success(BusinessCode businessCode, T data){
        Long code = HttpCode.OK.code() + ServiceCode.ADMIN.code() + TypeCode.NORMAL.code() + businessCode.code() + ErrorCode.P000.code();
        return new Result<T>().setCode(code).setData(data);
    }

    /**
     * 异常
     */
    public Result<T> fail(BusinessCode businessCode, ErrorCode errorCode){
        Long code = HttpCode.NOT_ACCEPTABLE.code() + ServiceCode.ADMIN.code() + TypeCode.BUSINESS.code() + businessCode.code() + errorCode.code();
        return new Result<T>().setCode(code);
    }

    public Result<T> fail(ProgramCode programCode, ErrorCode errorCode){
        Long code = HttpCode.NOT_ACCEPTABLE.code() + ServiceCode.ADMIN.code() + TypeCode.PROGRAM.code() + programCode.code() + errorCode.code();
        return new Result<T>().setCode(code);
    }

    public Result<T> fail(BusinessCode businessCode, ErrorCode errorCode, T data){
        Long code = HttpCode.NOT_ACCEPTABLE.code() + ServiceCode.ADMIN.code() + TypeCode.BUSINESS.code() + businessCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setData(data);
    }

    public Result<T> fail(ProgramCode programCode, ErrorCode errorCode, T data){
        Long code = HttpCode.NOT_ACCEPTABLE.code() + ServiceCode.ADMIN.code() + TypeCode.PROGRAM.code() + programCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setData(data);
    }

    /**
     * 其他错误，指定状态码
     */
    public Result<T> fail(HttpCode httpCode, BusinessCode businessCode, ErrorCode errorCode){
        Long code = httpCode.code() + ServiceCode.ADMIN.code() + TypeCode.BUSINESS.code() + businessCode.code() + errorCode.code();
        return new Result<T>().setCode(code);
    }

    public Result<T> fail(HttpCode httpCode, ProgramCode programCode, ErrorCode errorCode){
        Long code = httpCode.code() + ServiceCode.ADMIN.code() + TypeCode.PROGRAM.code() + programCode.code() + errorCode.code();
        return new Result<T>().setCode(code);
    }

    public Result<T> fail(HttpCode httpCode, BusinessCode businessCode, ErrorCode errorCode, T data){
        Long code = httpCode.code() + ServiceCode.ADMIN.code() + TypeCode.BUSINESS.code() + businessCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setData(data);
    }

    public Result<T> fail(HttpCode httpCode, ProgramCode programCode, ErrorCode errorCode, T data){
        Long code = httpCode.code() + ServiceCode.ADMIN.code() + TypeCode.PROGRAM.code() + programCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setData(data);
    }

    /**
     * 其他错误，指定状态码和微服务编码
     */
    public Result<T> fail(HttpCode httpCode, ServiceCode serviceCode, BusinessCode businessCode, ErrorCode errorCode){
        Long code = httpCode.code() + serviceCode.code() + TypeCode.BUSINESS.code() + businessCode.code() + errorCode.code();
        return new Result<T>().setCode(code);
    }

    public Result<T> fail(HttpCode httpCode, ServiceCode serviceCode, ProgramCode programCode, ErrorCode errorCode){
        Long code = httpCode.code() + serviceCode.code() + TypeCode.PROGRAM.code() + programCode.code() + errorCode.code();
        return new Result<T>().setCode(code);
    }

    public Result<T> fail(HttpCode httpCode, ServiceCode serviceCode, BusinessCode businessCode, ErrorCode errorCode, T data){
        Long code = httpCode.code() + serviceCode.code() + TypeCode.BUSINESS.code() + businessCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setData(data);
    }

    public Result<T> fail(HttpCode httpCode, ServiceCode serviceCode, ProgramCode programCode, ErrorCode errorCode, T data){
        Long code = httpCode.code() + serviceCode.code() + TypeCode.PROGRAM.code() + programCode.code() + errorCode.code();
        return new Result<T>().setCode(code).setData(data);
    }

}