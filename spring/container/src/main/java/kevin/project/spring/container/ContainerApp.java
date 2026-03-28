package kevin.project.spring.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(excludeFilters = @ComponentScan.Filter(classes = {SimpleConfig.class},type =
//        FilterType.ASSIGNABLE_TYPE))

public class ContainerApp {
    public static void main(String[] args) {
        SpringApplication.run(ContainerApp.class, args);
    }
}
