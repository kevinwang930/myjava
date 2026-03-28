package kevin.project.spring.container.service;

import kevin.project.spring.container.annotation.NestedAnnotation;
import kevin.project.spring.container.autoconfig.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

@NestedAnnotation
@Service
@Slf4j
public class SimpleService implements InitializingBean, ApplicationContextAware {

    @Value("${a:default}")
    private String a;

    private ApplicationContext applicationContext;

    public String sayHello(String name) {
        return String.format("Hello %s from host %s:%s\n", name, System.getenv("HOSTNAME"), System.getenv("SERVER_PORT"));
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
