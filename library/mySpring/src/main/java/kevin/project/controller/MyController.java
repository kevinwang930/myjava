package kevin.project.controller;

import kevin.project.service.MyService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {


    @Autowired
    MyService myService;
    @GetMapping("/mybatis")
    public void testMybatis() {
        myService.testMybatis();
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

@Getter
class Person {
    String name;
    Integer age;
}
