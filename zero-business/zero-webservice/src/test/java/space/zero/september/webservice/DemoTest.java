package space.zero.september.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import space.zero.september.webservice.entity.Person;

/**
 * @author : penggs
 * @program : september
 * @description :
 * @create : 2019-07-21 20:03
 */
public class DemoTest {
    public static void main(String[] args) {
        //创建动态客户端
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:10080/webservice/demo?wsdl");
        // 需要密码的情况需要加上用户名和密码
        //client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(2000);  //连接超时
        httpClientPolicy.setAllowChunking(false);    //取消块编码
        httpClientPolicy.setReceiveTimeout(120000);     //响应超时
        conduit.setClient(httpClientPolicy);
        //client.getOutInterceptors().addAll(interceptors);//设置拦截器
        try {
            Object[] objects = new Object[0];
            // invoke("方法名",参数1,参数2,参数3....);
            Person person = new Person();
            person.setFirstName("iii");
            person.setLastName("ooo");
            objects = client.invoke("sayHello", person);
            System.out.println("返回数据:" + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}