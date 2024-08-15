package kevin.project.runtime;

/**
 * @version 1.0
 * @ClassName RuntimeLearn
 * @Description TODO
 * @Date 2024/8/14
 **/
public class RuntimeLearn {

    public static void processorLearn() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


    public static void main(String[] args) {
        processorLearn();
    }
}
