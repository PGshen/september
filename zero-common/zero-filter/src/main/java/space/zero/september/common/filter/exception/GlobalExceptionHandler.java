package space.zero.september.common.filter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.returncode.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <project> september
 *
 * <p> 全局异常处理器
 *
 * @author penggs
 * @since 2020-09-06
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result exception(Exception e) {
        log.info("保存全局异常信息 ex={}", e.getMessage(), e);
        Result<String> result = new Result<>();
        ResultGen<String> resultGen = new ResultGen<>();
        if (e instanceof MethodArgumentNotValidException) {
            //获取参数校验错误集合
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            //格式化以提供友好的错误提示
            String message = String.format("参数校验错误（%s）：%s", fieldErrors.size(), fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(";")));
            //参数校验失败响应失败个数及原因
            result = resultGen.fail(HttpCode.NOT_ACCEPTABLE, ServiceCode.RESERVED, BusinessCode.ABNORMAL, ErrorCode.P308);
            result.setMessage(result.getMessage() + "\n" + message);
        } else {
            result = resultGen.fail(HttpCode.INTERNAL_ERROR, ServiceCode.RESERVED, BusinessCode.ABNORMAL, ErrorCode.P101);
        }

        return result;
    }


}