package kevin.project.spring.container.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
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
