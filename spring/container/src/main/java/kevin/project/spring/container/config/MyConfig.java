package kevin.project.spring.container.config;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @ClassName MyConfig
 * @Description TODO
 * @Date 12/14/25
 **/
@Configuration
@Log
public class MyConfig {

    @Bean
    public ConfigTest configTest(){
        log.info("create configTest");
        return new ConfigTest();
    }
}
