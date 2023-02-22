package kevin.project.jvm.type;

public class TypeLearn {
    public  void f() {
    String[] a = new String[2];
    Object[] b = a;
    a[0] = "hi";
    b[1] = Integer.valueOf(42);
}

    public static void main(String[] args) {
        TypeLearn typeLearn = new TypeLearn();
        typeLearn.f();
    }
}
