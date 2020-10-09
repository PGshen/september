package space.zero.september.auth.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.util.HtmlUtils;
import space.zero.september.common.core.returncode.BusinessCode;
import space.zero.september.common.core.returncode.ErrorCode;
import space.zero.september.common.core.returncode.ServiceCode;
import space.zero.september.common.core.returncode.TypeCode;

import java.io.IOException;
import java.util.Map;

import static org.springframework.security.oauth2.common.exceptions.OAuth2Exception.*;

/**
 * @author : penggs
 * @program : september
 * @description : 自定义异常序列化类
 * @create : 2019-09-22
 */
public class CustomOAuth2ExceptionJacksonSerializer extends StdSerializer<CustomOAuth2Exception> {
    private static final long HTTP_CODE_BASE = 100000000;

    public CustomOAuth2ExceptionJacksonSerializer() {
        super(CustomOAuth2Exception.class);
    }

    /**
     * 自定义返回类型
     *
     * @param value
	 * @param jgen
	 * @param serializerProvider
     * @author penggs
     * @date 2019-04-23
     */
    @Override
    public void serialize(CustomOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        String errorMessage = value.getMessage();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        int httpCode = value.getHttpErrorCode();
        String errorCodeStr = value.getOAuth2ErrorCode();
        String errorDescription = value.getCause().getMessage();
        ErrorCode errorCode = getExceptionType(errorCodeStr, errorDescription);
        jgen.writeStringField("code", httpCode * HTTP_CODE_BASE + ServiceCode.AUTH.code() + TypeCode.BUSINESS.code() + BusinessCode.AUTH.code() + errorCode.code() + "");
        jgen.writeStringField("message", errorCode.msg());
        jgen.writeStringField("data", errorMessage);
        jgen.writeEndObject();
    }

    /**
     * 获取对应的状态码
     *
     * @param errorCodeStr 错误码
     * @return ErrorCode
     * @author penggs
     * @date 2019-04-23
     */
    private ErrorCode getExceptionType(String errorCodeStr, String errorDescription){
        if (errorCodeStr == null) {
            return ErrorCode.P517;
        }
        // 无效的客户端
        switch (errorCodeStr) {
            case INVALID_CLIENT:
                return ErrorCode.P509;

            // 未授权的客户端
            case UNAUTHORIZED_CLIENT:
                return ErrorCode.P510;

            // 无效的认证类型
            case INVALID_GRANT:
                if ("Bad credentials".equals(errorDescription)) {
                    return ErrorCode.P501;
                } else if ("User account is locked".equals(errorDescription)) {
                    return ErrorCode.P519;
                } else if ("User account is disabled".equals(errorDescription)) {
                    return ErrorCode.P521;
                } else if ("User account is expired".equals(errorDescription)) {
                    return ErrorCode.P522;
                } else {
                    return ErrorCode.P515;
                }

                // 无效的scope
            case INVALID_SCOPE:
                return ErrorCode.P511;

            // 无效的token
            case INVALID_TOKEN:
                return ErrorCode.P512;

            // 无效的请求
            case INVALID_REQUEST:
                return ErrorCode.P513;

            // 重定向URL不匹配
            case REDIRECT_URI_MISMATCH:
                return ErrorCode.P514;

            // 不支持的认证类型
            case UNSUPPORTED_GRANT_TYPE:
                return ErrorCode.P515;

            // 不支持的响应类型
            case UNSUPPORTED_RESPONSE_TYPE:
                return ErrorCode.P516;

            // 访问拒绝
            case ACCESS_DENIED:
                return ErrorCode.P507;

            // 其他认证异常
            default:
                return ErrorCode.P517;
        }
    }
}