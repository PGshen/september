package space.zero.september.common.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: september
 * @description: 过滤器忽略的url和client
 * @author: penggs
 * @create: 2019-03-22
 **/
@Configuration
@Component
@ConditionalOnExpression("!'${security.ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "security.ignore")
public class SecurityIgnoreProperties {
    private List<String> urls = new ArrayList<>();
    private List<String> clients = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }
}
