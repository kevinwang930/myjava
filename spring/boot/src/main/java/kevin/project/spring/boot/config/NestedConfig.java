package kevin.project.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NestedConfig extends SimpleConfig {

    @Bean
    public Test hello2() {
        System.out.println("create nested test");
        return new Test();
    }
}
