package kevin.project.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:test.properties")
//@ComponentScan(excludeFilters = @ComponentScan.Filter(classes = {SimpleConfig.class},type =
//        FilterType.ASSIGNABLE_TYPE))

public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
}
