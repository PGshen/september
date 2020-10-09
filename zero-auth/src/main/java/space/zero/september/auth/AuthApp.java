package space.zero.september.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : penggs
 * @program : september
 * @description : 认证服务
 * @create : 2019-08-11 15:38
 */
@SpringCloudApplication
@ComponentScan(basePackages = { "space.zero.september.*" })
@EnableFeignClients
public class AuthApp {
    public static void main(String[] args){
        SpringApplication.run(AuthApp.class, args);
    }
}