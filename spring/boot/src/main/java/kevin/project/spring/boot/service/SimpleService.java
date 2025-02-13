package kevin.project.spring.boot.service;

import kevin.project.spring.boot.annotation.NestedAnnotation;
import kevin.project.spring.boot.annotation.SimpleAnnotation;
import org.springframework.stereotype.Service;

@NestedAnnotation
@Service
public class SimpleService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
