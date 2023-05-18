package kevin.project;


import java.util.function.BiFunction;

public class TryCatch {

    <R,T1,T2> R propagate(BiFunction<T1,T2,R> function, T1 t1, T2 t2) {
        try {
            return function.apply(t1,t2);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        new TryCatch().propagate((a,b)->a+b,1,2);
    }
}
