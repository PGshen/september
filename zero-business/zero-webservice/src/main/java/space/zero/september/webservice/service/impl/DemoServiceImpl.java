package space.zero.september.webservice.service.impl;

import space.zero.september.webservice.entity.Person;
import space.zero.september.webservice.service.DemoService;

import javax.jws.WebService;

/**
 * @author : penggs
 * @program : september
 * @description : webservice演示实现类
 * @create : 2019-07-21 17:35
 */
@WebService(name = "DemoService", targetNamespace = "http://service.webservice.september.zero.space", endpointInterface = "space.zero.september.webservice.service.DemoService")
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(Person person) {
        return "Hello, " + person.getLastName() + " " + person.getFirstName();
    }
}