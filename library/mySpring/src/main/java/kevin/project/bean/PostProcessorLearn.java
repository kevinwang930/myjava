package kevin.project.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @ClassName PostProcessorLearn
 * @Description TODO
 * @Date 8/22/24
 **/
@Component
@Slf4j
public class PostProcessorLearn implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LifeCycleLearn lifeCycleLearn) {
            log.info("this is life cycle bean");
            lifeCycleLearn.test = "test";
        }
        return bean;
    }

}
