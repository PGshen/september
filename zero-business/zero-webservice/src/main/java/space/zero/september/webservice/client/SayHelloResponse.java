
package space.zero.september.webservice.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sayHelloResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sayHelloResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sayHelloResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sayHelloResponse", propOrder = {
    "sayHelloResponse"
})
public class SayHelloResponse {

    protected String sayHelloResponse;

    /**
     * Gets the value of the sayHelloResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSayHelloResponse() {
        return sayHelloResponse;
    }

    /**
     * Sets the value of the sayHelloResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSayHelloResponse(String value) {
        this.sayHelloResponse = value;
    }

}
