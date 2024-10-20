package kevin.project.myspring.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @ClassName ExceptionService
 * @Description TODO
 * @Date 7/7/24
 **/
@Service
public class ExceptionService implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
//        throw new Exception("this is an exception");
    }
}
