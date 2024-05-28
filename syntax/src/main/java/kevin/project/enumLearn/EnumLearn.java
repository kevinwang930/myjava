package kevin.project.enumLearn;

enum Animal {
    CHICKEN(1),

    DUCK(2);
    private int species;

    Animal(int species) {
        this.species = species;
    }
}


public class EnumLearn {

    public static void valueOfLearn() {
        Animal animal = Animal.valueOf("DUCK");
        System.out.println(animal);
    }


    public static void main(String[] args) {
        valueOfLearn();
    }
}
