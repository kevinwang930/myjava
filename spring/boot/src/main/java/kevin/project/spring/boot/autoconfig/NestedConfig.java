package kevin.project.spring.boot.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@AutoConfiguration
public class NestedConfig extends SimpleConfig {

    @Bean
    @Primary
    public Test hello() {
        System.out.println("create nested test");
        return new Test();
    }
}
