package kevin.project.object;


import lombok.Data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ClassLearn {

    private int i;

    public void classTypeLearn() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InnerClass<String> stringInnerClass = new InnerClass<>();
        Method echo = stringInnerClass.getClass()
                                      .getDeclaredMethod("echo", Object.class);
        echo.setAccessible(true);
        echo.invoke(stringInnerClass, stringInnerClass);
        

        Class<?> clazz = stringInnerClass.getClass();
        System.out.println(Arrays.asList(clazz.getAnnotations()));
        System.out.println(Arrays.asList(clazz.getDeclaredAnnotations()));

        System.out.println(Arrays.asList(clazz.getTypeParameters()));
        System.out.println(clazz.isAssignableFrom(String.class));
    }

    public void testInnerClass() {
        InnerClass innerClass = new InnerClass();
        innerClass.showOuter();
    }

    public void instanceLearn() {
        Integer a = 3;
        if (a instanceof Object obj) {
            System.out.println(obj);
        }
    }

    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassLearn classLearn = new ClassLearn();
        classLearn.classTypeLearn();
        //        classLearn.testInnerClass();
        //        classLearn.instanceLearn();
    }


    @Data
    @SuppressWarnings("all")
    @Anno
    class InnerClass<T> {
        public void showOuter() {
            System.out.println(i);
        }

        public T echo(T test) {
            System.out.println(test);
            return test;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Anno {
    }
}
