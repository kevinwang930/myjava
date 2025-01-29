package kevin.project.cache.mapper;

import kevin.project.cache.bean.Worker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkerMapper {

    List<Worker> getAllWorkers();

    Worker getWorkerById(long id);

}
