package kevin.project.spring.kafka.service;

import kevin.project.spring.kafka.model.KafkaMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @ClassName KafkaService
 * @Description TODO
 * @Date 2/8/26
 **/
@Service
public class KafkaService {

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, KafkaMessage message) {
        kafkaTemplate.send(topic, message);
    }

}
