package kevin.project.controller;

import kevin.project.service.RedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@RestController
@RequestMapping("/redis")
public class RedisController {

    Logger logger = LogManager.getLogger();

    @Autowired
    private RedisService redisService;

    private ExecutorService service = new ScheduledThreadPoolExecutor(1);

    @GetMapping("/keys")
    @ResponseBody
    public Set<String> hello() {
        return redisService.keys();
    }


    @GetMapping("/list")
    @ResponseBody
    public void list() {
         redisService.getList("test");
    }
}

