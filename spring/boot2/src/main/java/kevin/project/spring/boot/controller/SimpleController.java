package kevin.project.spring.boot.controller;

import kevin.project.spring.boot.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    private SimpleService simpleService;

    @GetMapping("/test-sharding")
    public String property() {
        return simpleService.testSharding(); // Assuming a placeholder for the method logic
    }
}
