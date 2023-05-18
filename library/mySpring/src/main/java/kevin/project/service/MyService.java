package kevin.project.service;

import kevin.project.bean.User;
import kevin.project.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {

    private final UserMapper userMapper;

    MyService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void test() {
        System.out.println("this is a service");
    }

//    @Scheduled(fixedRate = 2000)
//    public void run() {
//        // Do something every 5 seconds
//        System.out.println("Scheduled task executed ");
//    }

    @Transactional
    public void testMybatis() {

        // Use the mapper to execute database operations
        User user = userMapper.findById(1L);
        System.out.println(user);

        // Other database operations
    }
}
