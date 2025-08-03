package kevin.project.spring.boot.mapper;

import kevin.project.spring.boot.entity.Worker;

public interface TestDbMapper {

    public Worker selectById(Integer id);
}
