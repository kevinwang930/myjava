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

    public static void main(String[] args) {
        objectLearn objectLearn = new objectLearn();
        objectLearn.print(new People());
        objectLearn.classLearn();
    }
}
