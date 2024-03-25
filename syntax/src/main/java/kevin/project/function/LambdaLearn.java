package kevin.project.function;

import java.util.List;

public class LambdaLearn {

    private static void lambdaLearn() {
        List<String> stringList = List.of("test1", "test");
        stringList.forEach(str-> System.out.println(str));
    }
    public static void main(String[] args) {
        lambdaLearn();
    }
}
