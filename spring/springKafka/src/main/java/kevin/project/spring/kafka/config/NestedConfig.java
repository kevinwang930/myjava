package kevin.project.spring.kafka.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@AutoConfiguration
@AutoConfigureBefore(SimpleConfig.class)
public class NestedConfig  {

    @Bean
    @Primary
    public Test hello() {
        System.out.println("create nested test");
        return new Test();
    }
}
