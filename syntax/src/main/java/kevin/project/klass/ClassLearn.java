package kevin.project.klass;

public class ClassLearn {
    public static void instanceLearn() {
        Class<?> klass = Integer.class;
        System.out.println(klass.isInstance(1));
    }

    public static void main(String[] args) {
        instanceLearn();
    }
}
