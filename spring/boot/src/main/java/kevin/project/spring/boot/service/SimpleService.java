package kevin.project.spring.boot.service;

import kevin.project.spring.boot.annotation.NestedAnnotation;
import kevin.project.spring.boot.annotation.SimpleAnnotation;
import kevin.project.spring.boot.config.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

@NestedAnnotation
@Service
@Slf4j
public class SimpleService implements InitializingBean, ApplicationContextAware {

    @Value("${a}")
    private String a;

    private ApplicationContext applicationContext;

    public String sayHello(String name) {
        return "Hello " + name;
    }

    public String getProperty() {
        return a;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Test> testBeans = applicationContext.getBeansOfType(Test.class);
        for (Map.Entry<String, Test> entry : testBeans.entrySet()) {
            log.info("test bean name: {}", entry.getKey());
            log.info("test bean value: {}", entry.getValue());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
