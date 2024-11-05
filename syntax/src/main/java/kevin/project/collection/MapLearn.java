package kevin.project.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapLearn {

    public static void hashMapLearn() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);


        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public static void concurrentHashMapLearn() throws InterruptedException {
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);

        Thread thread = new Thread(() -> {
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }

        });
        thread.start();
        map.remove("Two");
        map.remove("Three");
        map.remove("Four");
        thread.join();

    }

    public static void computeLearn() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);
        map.compute("one",(k,v) -> v == null ? 1 : v + 1);
        System.out.println(map);
    }


    public static void main(String[] args) throws InterruptedException {
        concurrentHashMapLearn();
        computeLearn();
    }
}
