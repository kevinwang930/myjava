package kevin.project.object;

class GenericClass<T> {
    private T t;

    public GenericClass(T t) {
        this.t = t;
    }

    public void print() {
        System.out.println(t);
    }
}

public class ClassLearn {
    public void classTypeLearn() {
        Class<?> clazz = String.class;
        System.out.println(clazz.isAssignableFrom(String.class));
    }

    public static void main(String[] args) {
        ClassLearn classLearn = new ClassLearn();
        classLearn.classTypeLearn();
    }
}
