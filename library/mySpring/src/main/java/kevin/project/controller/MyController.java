package kevin.project.controller;

import kevin.project.bean.User;
import kevin.project.model.Person;
import kevin.project.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {


    @Autowired
    MyService myService;
    @GetMapping("/mybatis")
    public User testMybatis() {
        return myService.testMybatis();
    }


    @GetMapping("/")
    @ResponseBody
    public Person hello() {
        Person person = new Person();
        person.name = "kevin";
        person.age = 23;
        return person;
    }
}

