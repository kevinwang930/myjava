package kevin.project;

import kevin.project.service.MyFirstBean;
import kevin.project.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyApplication {


    public static void testBeanProperty(ApplicationContext context) {
        MyFirstBean bean = context.getBean(MyFirstBean.class);
        System.out.println(bean.getNumber1());
//        MyBean bean = context.getBean(MyBean.class);
    }


    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("service.xml");
        MyService myService = context.getBean(MyService.class);
        myService.testMybatis();
        testBeanProperty(context);
    }
}
