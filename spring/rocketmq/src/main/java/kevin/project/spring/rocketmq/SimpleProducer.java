package kevin.project.spring.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public  String produce() {

        Message<String> message = MessageBuilder.withPayload("Hello World")
                                                   .build();
        rocketMQTemplate.send("TOPIC_TEST",message);
        return "success";
    }

    public  String produce(String message) {

        Message<String> message1 = MessageBuilder.withPayload(message)
                                                .build();
        rocketMQTemplate.send("TOPIC_TEST",message1);
        return "success";
    }
}
