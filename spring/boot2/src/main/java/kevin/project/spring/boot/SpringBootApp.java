package kevin.project.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(excludeFilters = @ComponentScan.Filter(classes = {SimpleConfig.class},type =
//        FilterType.ASSIGNABLE_TYPE))
public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
