package kevin.project;

public class Animal {

    String name;

    Animal() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void showName() {
        System.out.println(name);
    }
}
