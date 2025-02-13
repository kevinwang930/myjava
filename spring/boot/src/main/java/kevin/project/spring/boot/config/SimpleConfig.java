package kevin.project.spring.boot.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {

    @Bean
    @ConditionalOnMissingBean(Test.class)
    public Test hello() {
        System.out.println("create simple test");
        return new Test();
    }
}
