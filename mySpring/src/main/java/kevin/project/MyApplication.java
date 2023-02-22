package kevin.project;

import kevin.project.service.MyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyApplication {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("service.xml");
        MyService service = context.getBean(MyService.class);
        service.test();
        System.out.println(StringUtils.capitalize("test"));
    }
}
