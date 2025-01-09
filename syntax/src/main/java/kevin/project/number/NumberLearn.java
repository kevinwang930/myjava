package kevin.project.number;

/**
 * @version 1.0
 * @ClassName NumberLearn
 * @Description TODO
 * @Date 7/13/24
 **/
public class NumberLearn {


    public static void integerCompare() {
        Integer a = 100;
        Integer b = 100;
        System.out.println(a == b);

        a = 100000;
        b = 100000;
        System.out.println(a == b);


        int c = 100000;
        System.out.println(a == c);

    }

    public static void xandLearn() {
        System.out.println(1 & 3);
    }

    public static void main(String[] args) {
        xandLearn();
        integerCompare();
    }
}
