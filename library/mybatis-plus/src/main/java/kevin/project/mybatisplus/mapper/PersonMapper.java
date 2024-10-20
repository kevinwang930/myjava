package kevin.project.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kevin.project.mybatisplus.bean.Person;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @ClassName UserMapperPlus
 * @Description TODO
 * @Date 10/20/24
 **/
@Repository
public interface PersonMapper extends BaseMapper<Person> {
}
