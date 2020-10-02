package space.zero.september.common.log;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : penggs
 * @program : september
 * @description : 日志切面
 * @create : 2019-07-16
 */
@Aspect
@Component
public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
    LogInfo logInfo = new LogInfo();

    /**
     * 定义切面，web包下返回值为Result的
     *
     * @author penggs
     * @date 2019-03-26
     */
    @Pointcut("execution(public space.zero.september.common.core.Result *(..)) && execution(* space.zero.september..web..*.*(..))")
    public void pointCut() {
    }

    /**
     * 前置
     *
     * @param joinPoint 连接点
     * @author penggs
     * @date 2019-03-26
     */
    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Class<?> className = joinPoint.getTarget().getClass();
        logger = LoggerFactory.getLogger(className);
        Object[] args = joinPoint.getArgs();
        //序列化时过滤掉request，response，multipartFile,这些参数不可序列化
        List<Object> logArgs = streamOf(args)
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse) && !(arg instanceof MultipartFile)))
                .collect(Collectors.toList());
        logger.info("REQUEST_PARAMS: {}", JSONObject.toJSONString(logArgs));
        logInfo.setRequestParameter(JSONObject.toJSONString(logArgs));
    }

    /**
     * 后置
     *
     * @param joinPoint   连接点
     * @param returnValue 返回值
     * @author penggs
     * @date 2019-03-26
     */
    @AfterReturning(pointcut = "pointCut()", returning = "returnValue")
    public void doAfter(JoinPoint joinPoint, Object returnValue) {
        Class<?> className = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        logger = LoggerFactory.getLogger(className);
        logger.info("RESPONSE_VALUE: {}", JSONObject.toJSONString(returnValue));
        logInfo.setReturnValue(returnValue);
        logger.info("[METHOD = {}] WHOLE_INFO: {}", methodName, JSONObject.toJSONString(logInfo));
        MDC.clear();
    }

    /**
     * 环绕增强，记录请求信息
     *
     * @param joinPoint 连接点
     * @author penggs
     * @date 2019-03-26
     */
    @Around(value = "pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> className = joinPoint.getTarget().getClass();
        logger = LoggerFactory.getLogger(className);
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            logInfo.setRequestTime(new Date(startTime));
            logInfo.setRemoteAddr(request.getRemoteAddr());
            logInfo.setUserAgent(request.getHeader("user-agent"));
            logInfo.setMethod(request.getMethod());
            logInfo.setRequestUri(request.getRequestURI());
        }
        Object obj = null;
        obj = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logInfo.setTime(endTime - startTime);
        return obj;
    }

    private static <T> Stream<T> streamOf(T[] array) {
        return ArrayUtils.isEmpty(array) ? Stream.empty() : Arrays.stream(array);
    }
}