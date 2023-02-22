package kevin.project.klass;

import java.util.ArrayList;
import java.util.List;

class Animal {}

class Dog extends Animal {

}
public class boundLearn {

    public void addDog(List<? super Dog> animalList) {
        animalList.add(new Dog());
    }

    public void superBoundLearn() {
        List<Animal> animalList = new ArrayList<>();
        addDog(animalList);
        animalList.add(new Animal());
        animalList.get(0);

    }
}
