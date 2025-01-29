package kevin.project.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@MapperScan("kevin.project.cache.mapper")
public class CacheApp {

    public static void main(String[] args) {
        SpringApplication.run(CacheApp.class, args);
    }
}
