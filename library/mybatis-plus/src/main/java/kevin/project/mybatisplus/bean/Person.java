package kevin.project.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("Person")
public class Person {
    public Long id;

    public String name;
}
