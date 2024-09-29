package kevin.project.enumLearn;

import com.alibaba.fastjson2.JSON;

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

    public static void jsonEnumLearn() {
        Animal animal = Animal.valueOf("DUCK");
        String jsonString = JSON.toJSONString(animal);
        System.out.println(jsonString);
    }


    public static void main(String[] args) {
        valueOfLearn();
    }
}
