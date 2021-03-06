package space.zero.september.webservice.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.2
 * 2019-07-22T20:52:17.274+08:00
 * Generated source version: 3.3.2
 *
 */
@WebService(targetNamespace = "http://service.webservice.september.zero.space", name = "DemoService")
@XmlSeeAlso({ObjectFactory.class})
public interface DemoService {

    @WebMethod
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://service.webservice.september.zero.space", className = "space.zero.september.webservice.client.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://service.webservice.september.zero.space", className = "space.zero.september.webservice.client.SayHelloResponse")
    @WebResult(name = "sayHelloResponse", targetNamespace = "")
    public java.lang.String sayHello(

        @WebParam(name = "person", targetNamespace = "http://service.webservice.september.zero.space")
        space.zero.september.webservice.client.Person person
    );
}
