package kevin.project.spring.boot.service;

import kevin.project.spring.boot.annotation.NestedAnnotation;
import kevin.project.spring.boot.entity.Worker;
import kevin.project.spring.boot.mapper.TestDbMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NestedAnnotation
@Service
@Slf4j
public class SimpleService  {


    @Autowired
    private TestDbMapper testDbMapper;







    public String testSharding() {
        Worker worker = testDbMapper.selectById(1L);
        log.info("worker: {}", worker);
        if (worker == null) {
            return "null";
        }
        return worker.getName();
    }
}
