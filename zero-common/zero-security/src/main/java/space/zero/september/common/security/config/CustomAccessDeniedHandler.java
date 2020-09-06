package space.zero.september.common.security.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.returncode.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: september
 * @description: 自定义访问拒绝处理(已登录但没有权限）
 * @author: penggs
 * @create: 2019-03-22
 **/
@Component
public class CustomAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException {
        Result<Object> result = new Result<>();
        ResultGen<Object> resultGen = new ResultGen<>();
        log.info("拒绝访问，禁止访问 {}", request.getRequestURI());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        result = resultGen.fail(HttpCode.FORBIDDEN, ServiceCode.GATEWAY, BusinessCode.ABNORMAL, ErrorCode.P507);
        response.setStatus(HttpStatus.OK.value());
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONObject.toJSONString(result));
    }

}
