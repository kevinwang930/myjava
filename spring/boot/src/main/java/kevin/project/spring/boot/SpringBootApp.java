package kevin.project.spring.boot;

import kevin.project.spring.boot.config.NestedConfig;
import kevin.project.spring.boot.config.SimpleConfig;
import kevin.project.spring.boot.postprocessor.RegistryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:test.properties")
//@ComponentScan(excludeFilters = @ComponentScan.Filter(classes = {SimpleConfig.class},type =
//        FilterType.ASSIGNABLE_TYPE))

public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
