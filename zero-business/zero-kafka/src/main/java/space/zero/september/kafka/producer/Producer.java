package space.zero.september.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <project> september
 *
 * <p>
 *
 * @author penggs
 * @since 2020-09-10
 */
@RestController
public class Producer {
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @GetMapping("/message/send")
    public String send(String msg){
        kafkaTemplate.send("topic", msg); //使用kafka模板发送信息
        return "success";
    }
}