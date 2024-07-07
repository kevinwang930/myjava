package kevin.project.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
class User {
    private String name;
    private int age;
}


public class ListLearn {

    public static void listLearn() {
        var list  = new ArrayList<String>();
    }

    public static void containsLearn() {
        List<User> list  = new ArrayList();
        list.add(new User("kevin",1));
        list.add(new User("kevin1",12));
        System.out.println(list.contains(new User("kevin",2)));
    }

    public static void sortLearn() {
        List<User> list  = new ArrayList();
        list.add(new User("kevin",1));
        list.add(new User("kevin1",12));
    }


    public static void main(String[] args) {
        containsLearn();
    }


}
