package kevin.project.cache.controller;

import kevin.project.cache.bean.Worker;
import kevin.project.cache.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/list")
    @ResponseBody
    public List<Worker> list() {
        return workerService.getAllWorkers();
    }

}
