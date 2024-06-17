package kevin.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableOpenApi
@EnableSwagger2WebMvc
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
