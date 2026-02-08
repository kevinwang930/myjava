package kevin.project.spring.kafka.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @ClassName ConditionalConfig
 * @Description TODO
 * @Date 8/21/25
 **/

class ConditionalBean {

    public ConditionalBean() {
        System.out.println("ConditionalBean init");
    }
}

@Configuration
@ConditionalOnProperty(name = "config.condition", matchIfMissing = false)
public class ConditionalConfig {



    @Bean
    public ConditionalBean conditionalBean() {
        return new ConditionalBean();
    }

}
