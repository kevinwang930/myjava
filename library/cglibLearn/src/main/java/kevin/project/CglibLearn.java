package kevin.project;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.lang.reflect.InvocationTargetException;

public class CglibLearn {


    public static void proxyLearn() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "inside proxy";
            }
        });
        PersonService proxy = (PersonService) enhancer.create();

        System.out.println(proxy.sayHello("test"));
    }

    public static void intercepterLearn() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello Tom!";
            } else {
                return proxy.invokeSuper(obj, args);
            }
        });

        PersonService proxy = (PersonService) enhancer.create();
        System.out.println(proxy.sayHello("test"));
        System.out.println(proxy.lengthOfName("Mary"));

    }

    public static void fastClassLearn() throws NoSuchMethodException, InvocationTargetException {

        // Create a FastClass for the SampleClass
        FastClass fastClass = FastClass.create(PersonService.class);

        // Create a FastMethod for the 'test' method
        FastMethod fastMethod = fastClass.getMethod(PersonService.class.getMethod("sayHello", String.class));

        // Instantiate the SampleClass
        PersonService sampleInstance = new PersonService();

        // Invoke the 'test' method using the FastMethod
        Object result = fastMethod.invoke(sampleInstance, new Object[]{"world"});

        // Print the result
        System.out.println(result); // Output will be: Hello world
    }



    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException {
        proxyLearn();
        intercepterLearn();
        fastClassLearn();
    }
}
