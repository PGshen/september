package space.zero.september.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * <project> september
 *
 * <p> 服务网关
 *
 * @author penggs
 * @since 2020-09-06
 */
@SpringCloudApplication
public class GatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }
}