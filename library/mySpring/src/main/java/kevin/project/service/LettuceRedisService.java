package kevin.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * @version 1.0
 * @ClassName LettuceRedisService
 * @Description TODO
 * @Date 10/6/24
 **/
@Slf4j
@Service
public class LettuceRedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public void cache() {
        redisTemplate.execute(new CacheCallback());
    }


    private static class CacheCallback implements SessionCallback<Boolean> {

        @Override
        public Boolean execute(RedisOperations operations) throws DataAccessException {
            String key = "key";
            String valueKey = "key-value";
            operations.watch(key);
            operations.multi();
            operations.opsForValue()
                      .set(valueKey, System.currentTimeMillis(), Duration.ofSeconds(10));
            List<Object> count = operations.exec();
            log.info("exec count", count.size());
            return true;
        }
    }
}
