package kevin.project.springcloud.controller;

import kevin.project.springcloud.config.NacosConfig;
import kevin.project.springcloud.model.SimpleModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @ClassName SimpleController
 * @Description TODO
 * @Date 8/24/24
 **/
@Slf4j
@RestController
@RefreshScope
public class SimpleController {

//    @Autowired
//    private SimpleModel simpleModel;

    @Autowired
    private NacosConfig nacosConfig;


    @GetMapping("/simpleModel/name")
    public String getSimpleModelName() {
//        log.info("name : {}",simpleModel.getName());
        log.info("config name : {}",nacosConfig.getName());
        return nacosConfig.getName();
    }

}
