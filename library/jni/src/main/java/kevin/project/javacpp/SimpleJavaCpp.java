package kevin.project.javacpp;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Platform;

/**
 * @version 1.0
 * @ClassName SimpleJavaCpp
 * @Description TODO
 * @Date 2024/8/12
 **/
@Platform(include = "kevin/project/javacpp/plus.h")
public class SimpleJavaCpp {

    static {
        Loader.load();
    }

    static native int plus(int a, int b);

    public static void main(String[] args) {
        System.out.println("10 + 20 : " + plus(10, 20));
    }



}
