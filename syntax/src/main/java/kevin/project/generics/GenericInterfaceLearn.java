package kevin.project.generics;

import java.util.HashMap;
import java.util.Map;

interface Map1<K, V> {
    V get(K k);

    void put(K k, V v);
}

abstract class AbstractImpl<K, V> implements Map1<K, V> {
    Map<K, V> cache = new HashMap<>();

    @Override
    public V get(K k) {
        return cache.get(k);
    }

    public void put(K k, V v) {
        cache.put(k, v);
    }
}


public class GenericInterfaceLearn extends AbstractImpl<String, Map<String, Long>> {


    public static void main(String[] args) {
        GenericInterfaceLearn generic = new GenericInterfaceLearn();
        generic.put("a", Map.of("aa", 10L));
        System.out.println(generic.get("a")
                                  .get("aa"));
    }
}
