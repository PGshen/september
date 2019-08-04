package space.zero.september.admin.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.returncode.*;

import java.util.HashMap;

/**
 * @author : penggs
 * @program : september
 * @description : 登录
 * @create : 2019-07-27 12:00
 */
@Api(value = "login", tags = "登录模块")
@RestController
public class LoginWeb {
    private Logger log = LoggerFactory.getLogger(LoginWeb.class);

    @ApiOperation(value = "[登录]按用户名登录", notes = "返回 token", produces = "application/xml,application/json", tags = { "登录模块" }, response = Result.class)
    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody){
        log.info("Login: username:" + loginBody.getUsername() + ", password: " + loginBody.getPassword());
        return new Result<>().setCode(HttpCode.OK, ServiceCode.ADMIN, TypeCode.NORMAL, BusinessCode.NORMAL, ErrorCode.P000).setData(loginBody.getUsername());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class LoginBody{
        String username;
        String password;
    }
}