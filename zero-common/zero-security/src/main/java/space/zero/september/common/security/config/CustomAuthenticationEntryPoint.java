package space.zero.september.common.security.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.returncode.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: september
 * @description: 自定义401处理
 * @author: penggs
 * @create: 2019-03-07
 **/
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Result<Object> result = new Result<>();
        ResultGen<Object> resultGen = new ResultGen<>();
        log.info("未授权，禁止访问 {}", request.getRequestURI());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        result = resultGen.fail(HttpCode.UNAUTHORIZED, ServiceCode.GATEWAY, BusinessCode.ABNORMAL, ErrorCode.P508);
        response.setStatus(HttpStatus.OK.value());
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONObject.toJSONString(result));
    }
}

