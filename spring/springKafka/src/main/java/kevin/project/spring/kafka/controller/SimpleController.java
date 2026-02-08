package kevin.project.spring.kafka.controller;

import kevin.project.spring.kafka.model.KafkaMessage;
import kevin.project.spring.kafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
public class SimpleController {

    @Autowired
    private KafkaService kafkaService;


    @PostMapping("/kafka")
    public String kafka() {
        KafkaMessage message = new KafkaMessage(
                UUID.randomUUID().toString(),
                "hello",
                System.currentTimeMillis()
        );
        kafkaService.sendMessage("my-topic", message);
        return "success";
    }
}
