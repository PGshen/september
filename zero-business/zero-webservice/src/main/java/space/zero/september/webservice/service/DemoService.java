package space.zero.september.webservice.service;

import space.zero.september.webservice.entity.Person;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author : penggs
 * @program : september
 * @description : webservice演示
 * @create : 2019-07-21 17:35
 */
@WebService(name = "DemoService", targetNamespace = "http://service.webservice.september.zero.space")
public interface DemoService {
    @WebMethod(operationName = "sayHello")
    @WebResult(name = "sayHelloResponse", targetNamespace = "http://service.webservice.september.zero.space")
    String sayHello(@WebParam(name = "person", targetNamespace = "http://service.webservice.september.zero.space") Person person);
}