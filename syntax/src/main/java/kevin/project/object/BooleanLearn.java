package kevin.project.object;

/**
 * @version 1.0
 * @ClassName BooleanLearn
 * @Description TODO
 * @Date 2024/7/28
 **/
public class BooleanLearn {


    public  static void bitLearn() {
        System.out.println(false | true );
        System.out.println(false || true);
//        System.out.println(false | null);
        System.out.println(false & true);
        System.out.println(false && true);
    }

    public static void main(String[] args) {
        bitLearn();
    }
}
