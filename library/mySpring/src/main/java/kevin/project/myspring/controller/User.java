package kevin.project.myspring.controller;

import kevin.project.myspring.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class User {



    @GetMapping("/user")
    @ResponseBody
    public Person user(Principal principal) {
        Person person = new Person();
        person.name = principal.getName();
        person.age = 23;
        return person;
    }
}
