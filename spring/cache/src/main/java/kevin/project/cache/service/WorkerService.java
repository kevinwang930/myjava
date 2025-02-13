package kevin.project.cache.service;

import kevin.project.cache.bean.Worker;
import kevin.project.cache.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Transactional(rollbackFor = Exception.class)
    public List<Worker> getAllWorkers() {
        return workerMapper.getAllWorkers();
    }

}
