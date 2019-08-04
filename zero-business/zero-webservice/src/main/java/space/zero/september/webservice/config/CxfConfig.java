package space.zero.september.webservice.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.zero.september.webservice.service.DemoService;
import space.zero.september.webservice.service.impl.DemoServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author : penggs
 * @program : september
 * @description : cxf配置
 * @create : 2019-07-21 17:40
 */
@Configuration
public class CxfConfig {

    @Bean(name = "cxfServlet")
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(),"/webservice/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public DemoService demoService() {
        return new DemoServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), demoService());
        endpoint.publish("/demo");
        return endpoint;
    }

}