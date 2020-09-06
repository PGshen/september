package space.zero.september.common.security.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author : penggs
 * @program : peng
 * @description : 自动配置
 * @create : 2019-04-02
 */
@ComponentScan("space.zero.september.common.security")
public class ResourceServer3AutoConfiguration {
    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate lbRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
                    super.handleError(response);
                }
            }
        });
        return restTemplate;
    }

    @Bean
    @Primary
    public ResourceServerTokenServices resourceServerTokenServices() {
        return new CustomRemoteTokenServices();
    }
}