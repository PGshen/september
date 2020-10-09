package space.zero.september.auth.custom;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.returncode.BusinessCode;
import space.zero.september.common.core.returncode.ResultGen;
import space.zero.september.common.core.returncode.ServiceCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : penggs
 * @program : september
 * @description : 自定义认证成功处理（password）
 * @create : 2019-09-22
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String message;
        ResultGen<Authentication> resultGen = new ResultGen<>();
        Result<Authentication> result = resultGen.success(ServiceCode.AUTH, BusinessCode.AUTH, authentication);
        out.write(JSONObject.toJSONString(result));
        out.flush();
        out.close();
    }
}