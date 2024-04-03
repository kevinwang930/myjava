package kevin.project.dynamicProxy;

import java.lang.reflect.Proxy;

public class DynamicProxyLearn {

    public static void proxyLearn() {

        People people = (People) Proxy.newProxyInstance(DynamicProxyLearn.class.getClassLoader(),
                                                        new Class[]{People.class},
                                                        new DynamicInvocationHandler());

        System.out.println(people.name());

    }


    public static void main(String[] args) {
        proxyLearn();
    }

}
