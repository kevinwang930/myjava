package kevin.project.bean;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @ClassName LifeCycleLearn
 * @Description TODO
 * @Date 8/22/24
 **/
@Service
@Slf4j
public class LifeCycleLearn implements BeanNameAware {


    protected String test;


    @Value("${server.address}")
    private String address;

    @Override
    public void setBeanName(String name) {
      log.info("inside setBeanName method");
      log.info("address {}", address);
    }

    @PostConstruct
    public void init() {
        log.info("inside init method");
        log.info("test value {}", test);
        log.info("address {}", address);
    }
}
