package kevin.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "test")
public class Person {
    @Id
    public String id;
    public String name;
    public Integer age;
    Map<String,String> params;

    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
}
