package kevin.project;

import lombok.Data;
import lombok.val;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
class StreamTest {
    String name;

    Long age;

    Integer sex;
}

public class StreamLearn {

    public static void emptyStreamLearn() {
        Map<String, StreamTest> emptyMap = new HashMap<>();
        emptyMap.put("test", new StreamTest());
        Long s = emptyMap.values()
                         .stream()
                         .map(StreamTest::getAge)
                         .findFirst()
                         .orElse(null);
        System.out.println(s);
        System.out.println(emptyMap.get(null));
    }

    public static void main(String[] args) {
        emptyStreamLearn();
    }
}
