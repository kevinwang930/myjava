package kevin.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @GetMapping("/")
    @ResponseBody
    public Person hello() {
        Person person = new Person();
        person.name = "kevin";
        person.age = 23;
        return person;
    }
}

class Person {
    String name;
    Integer age;
}
