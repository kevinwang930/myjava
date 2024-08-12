package kevin.project.jni;


public class Simple {

    static {
        System.loadLibrary("simple");
    }


    static native int plus(int a, int b);


    public static void main(String[] args) {
        System.out.println("start main");
        System.out.println(plus(1, 2));
    }
}
