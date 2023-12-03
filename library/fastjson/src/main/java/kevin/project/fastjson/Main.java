package kevin.project.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class User {
    String name;
    String value;

}

public class Main {

    public void jsonObjectLearn() {
        User user = new User("kevin", "test");
        String userString = JSON.toJSONString(user);

        JSONObject jsonObject = JSON.parseObject(userString);
        System.out.println(jsonObject);
        System.out.println(jsonObject.get("name"));

        Object jsonObj2 = JSON.parse(userString);
        System.out.println(jsonObj2);
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.jsonObjectLearn();
    }
}