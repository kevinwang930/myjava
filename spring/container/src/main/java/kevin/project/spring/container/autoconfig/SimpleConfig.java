package kevin.project.spring.container.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class SimpleConfig {

    @Bean
    @ConditionalOnMissingBean(Test.class)
    public Test hello() {
        System.out.println("create simple test");
        return new Test();
    }
}
