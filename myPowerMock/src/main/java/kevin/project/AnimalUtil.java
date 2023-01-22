package kevin.project;

public class AnimalUtil {
    public static String getName() {
        Animal animal = new Animal();
        animal.setName("test");
        return animal.getName();
    }
}
