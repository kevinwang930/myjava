package kevin.project.spring.container.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import kevin.project.spring.container.service.SimpleService;

@RestController
public class SimpleController {

    @Autowired
    private SimpleService simpleService;

    @PostMapping("/property")
    public String property() {
        return simpleService.getProperty(); // Assuming a placeholder for the method logic
    }

    @GetMapping("/hello")
    public String hello() {
        return simpleService.sayHello("World");
    }
}
