package kevin.project.spring.controller;


import kevin.project.spring.rocketmq.SimpleProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private SimpleProducer simpleProducer;

    @RequestMapping("/test/{message}")
    public String test(@PathVariable("message") String message) {
        return simpleProducer.produce(message);
    }
}
