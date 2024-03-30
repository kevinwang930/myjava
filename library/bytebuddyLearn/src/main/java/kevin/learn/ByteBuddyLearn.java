package kevin.learn;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationTargetException;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class ByteBuddyLearn {

    public static void helloworld() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(ByteBuddyLearn.class.getClassLoader())
                .getLoaded();
        System.out.println(dynamicType.getDeclaredConstructor().newInstance().toString());
    }

    public static void proxyLearn() throws InstantiationException, IllegalAccessException {
        String r = new ByteBuddy()
                .subclass(PersonService.class)
                .method(named("sayHello")
                        .and(isDeclaredBy(PersonService.class)
                                .and(returns(String.class))))
                .intercept(MethodDelegation.to(new ProxyService(),"sayFoo"))
                .make()
                .load(ByteBuddyLearn.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .sayHello("test");
        System.out.println(r);

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        helloworld();
        proxyLearn();
    }
}
