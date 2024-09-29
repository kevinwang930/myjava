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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

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

    private ExecutorService service = new ScheduledThreadPoolExecutor(1);

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

    @GetMapping("/test-arg")
    public String testArg( Person person, @RequestParam Map<String,String> params) {
        return params
                     .toString();
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


    @PostMapping("/formtest")
    public String formtest(@RequestBody Person person, @ModelAttribute Person person1) {
        return person.getName() + person.getAge() + person1.getName() + person1.getAge();
    }
}

