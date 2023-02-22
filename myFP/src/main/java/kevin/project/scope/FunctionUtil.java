package kevin.project.scope;

import java.util.function.Consumer;

public class FunctionUtil {

    public static void try1(Runnable method) {
        System.out.println(method.getClass().getSimpleName());
        method.run();
    }

    public static void try2(Consumer<Integer> consumer,int number) {
        System.out.println(consumer.getClass().getName());
        System.out.println(consumer.getClass().getCanonicalName());
        consumer.accept(number);
    }
}
