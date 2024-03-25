package kevin.project.function;

import java.util.function.Function;

public class FunctionalInterfaceLearn {


    private String hello(String message) {
        System.out.println("inside hello method");
        return message;
    }



    public <A,T> void functionLearn(Function<A,T> f, A message) {
        T result =  f.apply(message);
        System.out.println(result);
    }

    public static void main(String[] args) {
        FunctionalInterfaceLearn functionalInterfaceLearn = new FunctionalInterfaceLearn();
        functionalInterfaceLearn.functionLearn(functionalInterfaceLearn::hello,"hello");
    }
}
