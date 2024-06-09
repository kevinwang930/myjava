package kevin.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@ImportResource("classpath:/service.xml")
public class MyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWebApplication.class, args);
    }



}
