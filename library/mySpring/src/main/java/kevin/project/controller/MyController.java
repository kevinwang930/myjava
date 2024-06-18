package kevin.project.controller;

import kevin.project.bean.User;
import kevin.project.model.Person;
import kevin.project.service.MyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    Logger logger = LogManager.getLogger();

    @Autowired
    MyService myService;

    @Autowired
    Environment env;

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
}

