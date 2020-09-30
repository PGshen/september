package space.zero.september.gateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import space.zero.september.common.core.returncode.*;

import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * @author : penggs
 * @program : september
 * @description : 熔断降级
 * @create : 2019-09-12
 */
@Component
public class HystrixFallbackHandler implements HandlerFunction<ServerResponse> {
    private static Logger log = LoggerFactory.getLogger(HystrixFallbackHandler.class);

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);

        originalUris.ifPresent(originalUri -> log.error("网关执行请求:{}失败,hystrix服务降级处理", originalUri));

        ResultGen<Object> resultGen = new ResultGen<>();

        return ServerResponse.status(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(resultGen.fail(HttpCode.SERVICE_UNAVAILABLE, ServiceCode.GATEWAY, ProgramCode.ABNORMAL, ErrorCode.P601)));
    }
}