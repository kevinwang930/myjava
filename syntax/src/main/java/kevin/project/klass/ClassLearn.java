package kevin.project.klass;

import java.net.URL;

public class ClassLearn {
    public static void instanceLearn() {
        Class<?> klass = Integer.class;
        System.out.println(klass.isInstance(1));
    }

    public static void moduleLearn() {
        Module module = ClassLearn.class.getModule();
        System.out.println(module.getName());
    }

    public static void resourceLearn() {
        URL url = ClassLearn.class.getResource("/test.txt");
        System.out.println(url);
    }

    public static void main(String[] args) {
        instanceLearn();
        moduleLearn();
        resourceLearn();
    }
}
