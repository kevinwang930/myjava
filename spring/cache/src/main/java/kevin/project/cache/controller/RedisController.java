package kevin.project.cache.controller;

import kevin.project.cache.service.JedisService;
import kevin.project.cache.service.LettuceCacheService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisController {

    Logger logger = LogManager.getLogger();

    @Autowired
    private JedisService redisService;

    @Autowired
    private LettuceCacheService lettuceCacheService;

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

    @GetMapping("/test-script")
    public Long cache() {
        return lettuceCacheService.testScript();
    }

    @GetMapping("/test-string")
    public Long testString() {
         lettuceCacheService.testString("test");
         return 1L;
    }
}
