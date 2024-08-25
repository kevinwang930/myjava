package kevin.project.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @version 1.0
 * @ClassName NacosApplication
 * @Description TODO
 * @Date 8/24/24
 **/
@SpringBootApplication
@EnableConfigurationProperties
//@EnableNacosConfig
public class NacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }
}
