package kevin.project.spring.kafka.service;

import kevin.project.spring.kafka.model.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @ClassName KafkaConsumer
 * @Description TODO
 * @Date 2/8/26
 **/
@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "my-topic", groupId = "my-consumer-group")
    public void consume(KafkaMessage message) {
        logger.info("Consumed message: {}", message);
        System.out.println("Received message: " + message);
    }

}
