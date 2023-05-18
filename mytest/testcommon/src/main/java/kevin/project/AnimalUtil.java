package kevin.project;

public final class AnimalUtil {
    public static String getName() {
        Animal animal = new Animal();
        animal.setName("test");
        return animal.getName();
    }

    public static String getName2() {
        return "test";
    }

    public String getNameInstance() {
        return "test";
    }
}
