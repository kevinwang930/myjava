package kevin.project.controller;

import kevin.project.bean.User;
import kevin.project.model.Person;
import kevin.project.service.ClientPushService;
import kevin.project.service.MyService;
import kevin.project.service.StreamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

@RestController
public class MyController {

    Logger logger = LogManager.getLogger();

    @Autowired
    MyService myService;

    @Autowired
    Environment env;

    @Autowired
    private ClientPushService clientPushService;

    @Autowired
    private StreamService streamService;

    private ExecutorService service = new ScheduledThreadPoolExecutor(1) ;

    @GetMapping("/mybatis")
    public User testMybatis() {
        return myService.testMybatis();
    }

    @GetMapping("/test")
    public void test() {
        myService.test();
    }

    @GetMapping("/")
    @ResponseBody
    public Person hello() {

        logger.info(env.getProperty("server.port"));
        Person person = new Person();
        person.name = "kevin";
        person.age = 23;
        return person;
    }

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sse() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        clientPushService.addEmitter(sseEmitter);
        return sseEmitter;
    }


    @GetMapping("/stream")
    public String stream() {
        try {
            return streamService.getFootballMatchInfo();
        } catch (Exception e) {
            return "error";
        }
    }
}

