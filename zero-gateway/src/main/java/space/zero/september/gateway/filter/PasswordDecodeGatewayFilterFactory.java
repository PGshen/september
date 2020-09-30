package space.zero.september.gateway.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 密码解码
 * @create : 2019-09-13
 */
@Component
public class PasswordDecodeGatewayFilterFactory extends AbstractGatewayFilterFactory {
    private static final Logger log = LoggerFactory.getLogger(PasswordDecodeGatewayFilterFactory.class);

    private static final String PASSWORD = "password";
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/NOPadding";
    private static final String OAUTH_TOKEN_URL = "/oauth/token";
    @Value("${security.encode.key:1234567887654321}")
    private String key;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), OAUTH_TOKEN_URL)) {
                return chain.filter(exchange);
            }

            URI uri = exchange.getRequest().getURI();
            String queryParam = uri.getRawQuery();
            Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CharsetUtil.UTF_8);

            String password = paramMap.get(PASSWORD);
            if (StrUtil.isNotBlank(password)) {
                try {
                    password = decryptAES(password, key);
                } catch (Exception e) {
                    log.error("密码解密失败:{}", password);
                    return Mono.error(e);

                }
                paramMap.put(PASSWORD, password.trim());
            }

            URI newUri = UriComponentsBuilder.fromUri(uri)
                    .replaceQuery(HttpUtil.toParams(paramMap))
                    .build(true)
                    .toUri();

            ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(newRequest).build());
        };
    }

    private static String decryptAES(String data, String pass) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        SecretKeySpec keyspec = new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM);
        IvParameterSpec ivspec = new IvParameterSpec(pass.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
        byte[] result = cipher.doFinal(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
        return new String(result, StandardCharsets.UTF_8);
    }
}