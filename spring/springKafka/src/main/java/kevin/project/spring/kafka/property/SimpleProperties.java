package kevin.project.spring.kafka.property;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Component
public class SimpleProperties implements InitializingBean {


    @Value("${a}")
    private Integer a;

    @Value("#{'${b:}'.split(',')}")
    private List<String> trimA;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("a: " + a);
        System.out.println("trimA: " + trimA);
    }
}
