package kevin.project.service;

import kevin.project.bean.User;
import kevin.project.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {

    private final Logger logger = LogManager.getLogger();

    private final UserMapper userMapper;

    MyService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void test() {
        logger.info("this is a service");
    }

//    @Scheduled(fixedRate = 2000)
//    public void run() {
//        // Do something every 5 seconds
//        System.out.println("Scheduled task executed ");
//    }

    @Transactional(rollbackFor = Exception.class)
    public void testMybatis() {

        // Use the mapper to execute database operations
        User user = userMapper.findById(1L);
        System.out.println(user);

        // Other database operations
    }
}
