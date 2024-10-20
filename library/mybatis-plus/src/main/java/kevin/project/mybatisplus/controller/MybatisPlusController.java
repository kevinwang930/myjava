package kevin.project.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import kevin.project.mybatisplus.bean.Person;
import kevin.project.mybatisplus.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @ClassName MybatisPlusController
 * @Description TODO
 * @Date 10/20/24
 **/
@RestController
@RequestMapping("/mybatis-plus")
public class MybatisPlusController {

    @Autowired
    private PersonMapper personMapper;

    @GetMapping
    public List<Person> person() {
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "li");
        return personMapper.selectList(queryWrapper);
    }

}
