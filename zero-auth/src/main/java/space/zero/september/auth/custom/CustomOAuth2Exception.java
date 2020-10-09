package space.zero.september.auth.custom;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author : penggs
 * @program : september
 * @description : 自定义认证异常处理
 * @create : 2019-09-22
 */
@JsonSerialize(using = CustomOAuth2ExceptionJacksonSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {
    public CustomOAuth2Exception(String msg, Throwable t){
        super(msg, t);
    }

    public CustomOAuth2Exception(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode(){
        return this.getMessage();
    }
}