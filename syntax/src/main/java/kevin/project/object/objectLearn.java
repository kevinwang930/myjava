package kevin.project.object;


class People {
    private String name;

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Animal {
    private String name;

    public void shout() {
        System.out.println("animal shout");
    }

    public static void staticShout() {
        System.out.println("animal shout");
    }
}

class Duck extends Animal {
    @Override
    public void shout() {
        System.out.println("duck shout");
    }

    public static void staticShout() {
        System.out.println("duck shout");
    }
}

public class objectLearn {
    public void print(Object obj) {
        System.out.println(obj.toString());
    }

    public void animalShout(Animal animal) {
        animal.shout();
        animal.staticShout();
    }

    public void classLearn() {
        Duck duck = new Duck();
        animalShout(duck);

    }

    public void equalsLearn() {
        int a = 1;
        int b = 1;
        System.out.println(a == b);
        Integer a1 = 1;
        Integer b1 = 1;
        System.out.println(a1 == b1);
        System.out.println(a1.equals(b1));
        String s1 = "abcd";
        String s2 = "abcd";
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }

    public static void main(String[] args) {
        objectLearn objectLearn = new objectLearn();
        objectLearn.print(new People());
        objectLearn.classLearn();
        objectLearn.equalsLearn();
    }
}
