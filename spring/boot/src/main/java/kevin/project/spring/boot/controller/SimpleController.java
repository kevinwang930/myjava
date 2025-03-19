package kevin.project.spring.boot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import kevin.project.spring.boot.service.SimpleService;

@RestController
public class SimpleController {

    @Autowired
    private SimpleService simpleService;

    @PostMapping("/property")
    public String property() {
        return simpleService.getProperty(); // Assuming a placeholder for the method logic
    }
}
