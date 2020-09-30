package space.zero.september.common.filter.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author : penggs
 * @program : september
 * @description : feign请求拦截器
 * @create : 2020-04-03 15:20
 */
@Configuration
@Setter
public class FeignReqInterceptor implements RequestInterceptor {
    private Logger log = LoggerFactory.getLogger(FeignReqInterceptor.class);

    private static final String SPECIAL_URI = "/special-uri";

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignReqInterceptor();
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }
        // 拦截指定uri, 做处理，例如在header加参数
        if (requestTemplate.path().startsWith(SPECIAL_URI)) {
            log.debug("intercept the special uri: {}", SPECIAL_URI);
            String timestamp = String.valueOf(System.currentTimeMillis());
            requestTemplate.header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
            requestTemplate.header("timestamp", timestamp);
        }
        // feign调用标识，用于区别于普通请求
        requestTemplate.header("feignRequest", "yes");
    }
}