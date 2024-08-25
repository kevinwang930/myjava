package kevin.project.springcloud.config;

/**
 * @version 1.0
 * @ClassName NacosConfig
 * @Description TODO
 * @Date 8/24/24
 **/

import com.alibaba.nacos.api.config.annotation.NacosValue;
import kevin.project.springcloud.model.SimpleModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "nacos.test")
@RefreshScope
@Data
public class NacosConfig {

//    @NacosValue(value = "${nacos.test.name:default_value}", autoRefreshed = true)
    private String name;


//    @Bean
//    public SimpleModel nacosTestBean() {
//        return new SimpleModel(name);
//    }
}

