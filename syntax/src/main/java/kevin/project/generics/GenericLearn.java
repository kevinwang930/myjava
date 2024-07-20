package kevin.project.generics;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @version 1.0
 * @ClassName GenericLearn
 * @Description TODO
 * @Date 7/17/24
 **/





public class GenericLearn {
    static class R<T> {
        T data;
    }
    interface MyService {
        R<List<String>> getData();
    }

    @Data
    static class GenericTest<T> {
        T value;
    }

    @AllArgsConstructor
    @Data
    static class Animal {
        String name;
        Boolean fly;
    }

    @Data
    @AllArgsConstructor
    static class Bird {
        Integer age;
        String  type;
    }

    static <T> T createProxy(Class<T> interfaceType) {
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[] { interfaceType },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) {
                        return null;
                    }
                }
        );
    }

    static void genericSerialization() {
        Animal animal = new Animal("test",true);
        Bird wing = new Bird(3, "wing");

        GenericTest<Animal> g1 = new GenericTest<>();
        g1.value = animal;
        String s1 = JSON.toJSONString(g1);
        System.out.println(s1);
        GenericTest d1 = JSON.parseObject(s1, GenericTest.class);
        JSONObject a1 = (JSONObject) d1.value;
        Animal ac1 = a1.toJavaObject(Animal.class);
        System.out.println(ac1);


//        Animal a1 = (Animal) d1.value;
        System.out.println(a1.getString("name"));
    }

    public static void main(String[] args) {
        MyService proxy = createProxy(MyService.class);
        Method method = proxy.getClass().getInterfaces()[0].getMethods()[0];
        Type returnType = method.getGenericReturnType();
        System.out.println(returnType);
        genericSerialization();
    }
}
