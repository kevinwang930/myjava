package kevin.project.spring.boot.mapper;

import kevin.project.spring.boot.entity.Worker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDbMapper {

    public Worker selectById(Long id);
}
