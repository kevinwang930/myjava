package kevin.project.klass;

public class Type {

    public static void main(String[] args) {
        Base base = new Base();
        Person person = (Person) base;
        person.sex = "test";
        System.out.println(person.name);
        System.out.println(person.sex);
    }
}


class Base {
    String name;

    int age;
}

class Person extends Base {
    String sex;
}


