package space.zero.september.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <project> september
 *
 * <p> test
 *
 * @author penggs
 * @since 2020-09-10
 */
@SpringCloudApplication
@ComponentScan("space.zero.september.*")
public class KafkaApp {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApp.class, args);
    }
}