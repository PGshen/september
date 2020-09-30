package space.zero.september.gateway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import space.zero.september.gateway.handler.HystrixFallbackHandler;

/**
 * @author : penggs
 * @program : september
 * @description : 路由配置
 * @create : 2019-09-12
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {
    private final HystrixFallbackHandler hystrixFallbackHandler;

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.path("/fallback")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), hystrixFallbackHandler);

    }

}