package kevin.project.lambda;

interface Person {
    String name();

    default String greet() {
        return " Hi , I ’m " + this.name() + " ; nice to meet you ! ";
    }
}


interface GamerPerson extends Person {
    default String greet() {
        return " Hi , I ’m " + this.name() + " ; and I love computer games ! ";
    }
}


public class LambdaLearn {
    public void lambdaLearn() {
        Person bob = () -> " bob ";
        System.out.println(bob.greet());

        Person p = (GamerPerson) () -> " charles ";
        System.out.println(p.greet()); // dynamic dispatch
    }


    public void lambdaThreadLearn() throws InterruptedException {
        int a = 1;
        Thread thread = new Thread(() -> System.out.println(a));
        thread.start();
//        a = 2;
        System.out.println("after thread start");
        thread.join();
        System.out.println(a);
    }

    public static void main(String[] args) {
        LambdaLearn lambdaLearn = new LambdaLearn();
        lambdaLearn.lambdaLearn();
    }
}
