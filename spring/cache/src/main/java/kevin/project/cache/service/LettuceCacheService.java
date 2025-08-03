package kevin.project.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @version 1.0
 * @ClassName LettuceService
 * @Description TODO
 * @Date 10/23/24
 **/
@Slf4j
@Service
public class LettuceCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    private DefaultRedisScript<Long> defaultScript = new DefaultRedisScript<>(

             "local current = redis.call('get', KEYS[1])\n"
            + "if current and tonumber(current) >= tonumber(ARGV[1]) then\n"
            + "    return tonumber(current)\n"
            + "else\n"
            + "    redis.call('set', KEYS[1], ARGV[1])\n"
            + "    redis.call('expire', KEYS[1], tonumber(ARGV[2]))\n"
            + "    return tonumber(ARGV[1])\n"
            + "end",
            Long.class);

    private  static final String SCRIPT_SMALL =
            "local current = redis.call('get', KEYS[1]) " +
                    "if current and tonumber(current) <= tonumber(ARGV[1]) then " +
                    "    return '0' " +
                    "else " +
                    "    redis.call('set', KEYS[1], ARGV[1], 'ex', ARGV[2]) " +
                    "    return '1' " +
                    "end";



    public Long testScript() {
        Long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        return redisTemplate.execute(defaultScript, List.of("12345"),  System.currentTimeMillis(),
                64700000000L);
    }

    public void testString(String content) {
        redisTemplate.opsForValue().set("test", content);
    }

    public boolean testSmall() {
        DefaultRedisScript<String> defaultScript = new DefaultRedisScript<>(SCRIPT_SMALL, String.class);
        Long tp = -1L;
        Integer expireSeconds = 14500;
        String result = redisTemplate.execute(defaultScript, RedisSerializer.string(),RedisSerializer.string(),
                List.of("dsph:123_456_789"), String.valueOf(tp),
                String.valueOf(expireSeconds));
        return Integer.parseInt(result) == 1;
    }

    public void testSession() {
        redisTemplate.executePipelined(new SessionCallback<Object>() {

            @Override
            public  Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForValue().set("test1","test2");
                operations.opsForSet().add("set1",1L,2L,3L,123456987687L);
                return null;
            }
        });
    }

    public void testTransaction() {
        List<Object> objects = redisTemplate.executePipelined(new SessionCallback<Object>() {

            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue()
                          .set("test1", "test2");
                operations.opsForSet()
                          .add("set1", 1L, 2L, 3L, 123456987687L);
                operations.opsForSet()
                          .members("set1");
                operations.exec();
                return null;
            }
        });
        if (CollectionUtils.isNotEmpty(objects)) {
            objects.forEach(o -> log.info("{}", o));
            List<Object> result = (List<Object>) objects.get(0);

            Set<Long> members = (Set<Long>) result.get(2);
            members.forEach(System.out::println);
        }
    }
}
