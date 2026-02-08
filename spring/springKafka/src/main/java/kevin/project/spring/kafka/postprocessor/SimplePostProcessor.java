package kevin.project.spring.kafka.postprocessor;


import kevin.project.spring.kafka.annotation.NestedAnnotation;
import kevin.project.spring.kafka.annotation.SimpleAnnotation;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

@Service
public class SimplePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        SimpleAnnotation ann = targetClass.getAnnotation(SimpleAnnotation.class);
        if (ann != null) {
            System.out.println("simple annotation found");
        }

        NestedAnnotation nestedAnnotation = targetClass.getAnnotation(NestedAnnotation.class);
        if (nestedAnnotation != null) {
            System.out.println("nested annotation found");
        }

        return bean;
    }
}
