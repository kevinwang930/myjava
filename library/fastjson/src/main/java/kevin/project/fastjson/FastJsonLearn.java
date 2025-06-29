package kevin.project.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
class User {
    String name;
    String value;
    Long longValue = 3L;
    String test;
    transient String transientTest = "default";

    public void setTest1(String test1) {
        this.test = test1;
    }

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
        user.setTest1("test123");
        String userString = JSON.toJSONString(user, JSONWriter.Feature.WriteClassName);
        System.out.println(userString);
        JSONObject jsonObject = JSON.parseObject(userString);
        User parsedUser = jsonObject.to(User.class);
        System.out.println(parsedUser);
        System.out.println(jsonObject.get("name"));
        System.out.println(parsedUser.getName());
    }

    public void jsonStringLearn() {
        val jsonString = JSON.toJSONString("test");
        System.out.println(jsonString);
        System.out.println(jsonString.equals("test"));
    }

    public void jsonLongLearn() {
        val jsonString = JSON.toJSONString(123L);
        System.out.println(jsonString);
    }

    public void jsonSetterLearn() {
        User user = new User("kevin", "test");
        user.setTest1("test123");
        String userString = JSON.toJSONString(user, JSONWriter.Feature.WriteClassName);
        System.out.println(userString);
        User parsedUser = JSON.parseObject(userString,User.class);
        System.out.println(parsedUser);
        System.out.println(parsedUser.getTest());
    }

    public void jsonReaderLearn() {
        User user = new User("kevin", "test");
        String userString = JSON.toJSONString(user, JSONWriter.Feature.WriteClassName);
        System.out.println(userString);
        Object result = JSON.parseObject(userString, Object.class, JSONReader.Feature.SupportAutoType);
        System.out.println(result);
        System.out.printf(result.getClass().getName());
        System.out.println();
    }

    public static void transientLearn() {
        val user = new User("kevin", "test");
        user.setTransientTest("test");
        val userString = JSON.toJSONString(user, JSONWriter.Feature.WriteClassName);
        System.out.println(userString);
        val parsedUser = JSON.parseObject(userString,User.class);
        System.out.println(parsedUser);
        System.out.println(parsedUser.getTransientTest());
    }

    public static void main(String[] args) {
        FastJsonLearn fastJsonLearn = new FastJsonLearn();
//        fastJsonLearn.jsonObjectLearn();
//        fastJsonLearn.jsonReaderLearn();
//        fastJsonLearn.jsonLongLearn();
//        fastJsonLearn.jsonStringLearn();
        fastJsonLearn.jsonSetterLearn();
        transientLearn();
    }
}
