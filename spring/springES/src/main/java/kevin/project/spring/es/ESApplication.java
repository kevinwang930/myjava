package kevin.project.spring.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:test.properties")
//@ComponentScan(excludeFilters = @ComponentScan.Filter(classes = {SimpleConfig.class},type =
//        FilterType.ASSIGNABLE_TYPE))

public class ESApplication {
    public static void main(String[] args) {
        SpringApplication.run(ESApplication.class, args);
    }
}
