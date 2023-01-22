package kevin.project;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoadLearn {

    public void loadBaseModule() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File jarFile =  new File("/code/myJava/myModule/baseModule/target/baseModule-1.0-SNAPSHOT.jar");

        URL url = jarFile.toURI().toURL();
        URL[] urls = new URL[]{url};
        ClassLoader classLoader = new URLClassLoader(urls);
        Class<?> targetClass = classLoader.loadClass("kevin.project.Main");
        Method method = targetClass.getDeclaredMethod("main",String[].class);
        String[] args = new String[]{};
        method.invoke(null,(Object) args);


    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        ClassLoadLearn classLoadLearn = new ClassLoadLearn();
        classLoadLearn.loadBaseModule();
    }
}