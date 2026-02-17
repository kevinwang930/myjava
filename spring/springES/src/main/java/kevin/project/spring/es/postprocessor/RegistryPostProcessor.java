package kevin.project.spring.es.postprocessor;

import kevin.project.spring.es.config.NestedConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

public class RegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        registry.registerBeanDefinition("nestedConfig", BeanDefinitionBuilder.rootBeanDefinition(NestedConfig.class)
                                                                             .getBeanDefinition());
    }
}
