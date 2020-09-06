package space.zero.september.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import space.zero.september.common.security.annotation.EnableResourceServer3;
import space.zero.september.common.swagger.annotation.EnableSwagger3;

/**
 * @author : penggs
 * @program : september
 * @description : 系统管理服务
 * @create : 2019-07-15 20:53
 */
@EnableSwagger3
@SpringCloudApplication
@EnableResourceServer3
@ComponentScan("space.zero.september.*")
public class AdminApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class, args);
    }
}