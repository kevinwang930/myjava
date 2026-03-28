package kevin.project.spring.container.mapper;

import kevin.project.spring.container.entity.Worker;

public interface TestDbMapper {

    public Worker selectById(Integer id);
}
