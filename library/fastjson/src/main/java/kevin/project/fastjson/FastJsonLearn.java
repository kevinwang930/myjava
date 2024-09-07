package kevin.project.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
class User {
    String name;
    String value;
    Long longValue = 3L;

    List<HashMap<Long, Integer>> mp;

    User(String name, String value) {
        this.name = name;
        this.value = value;
        mp = new ArrayList<>();
        HashMap<Long, Integer> longIntegerHashMap = new HashMap<>();
        longIntegerHashMap.put(3345L, 1);
        mp.add(longIntegerHashMap);
    }
}

public class FastJsonLearn {


    public void jsonObjectLearn() {
        User user = new User("kevin", "test");
        String userString = JSON.toJSONString(user, JSONWriter.Feature.WriteClassName);
        System.out.println(userString);
        JSONObject jsonObject = JSON.parseObject(userString);
        System.out.println(jsonObject.get("name"));
        User javaObject = jsonObject.toJavaObject(User.class);
        System.out.println(javaObject.getName());
    }

    public void jsonReaderLearn() {
        User user = new User("kevin", "test");
        String userString = JSON.toJSONString(user, JSONWriter.Feature.WriteClassName);
        System.out.println(userString);
        Object result = JSON.parseObject(userString, Object.class, JSONReader.Feature.SupportAutoType);
        System.out.println(result);
        System.out.printf(result.getClass().getName());
    }

    public static void main(String[] args) {
        FastJsonLearn fastJsonLearn = new FastJsonLearn();
        fastJsonLearn.jsonObjectLearn();
        fastJsonLearn.jsonReaderLearn();
    }
}