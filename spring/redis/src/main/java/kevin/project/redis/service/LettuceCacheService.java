package kevin.project.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

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

    private static String getCacheScript;


    static  {
        try (InputStream is = LettuceCacheService.class.getResourceAsStream("/lua/get_cache.lua")) {
            Objects.requireNonNull(is);
            getCacheScript = IOUtils.toString(is, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("get lua script failed", e);
        }
    }


    public List<Integer> get() {

        RedisScript<List> redisScript = new DefaultRedisScript<>(getCacheScript,List.class);
        List<Object> result = redisTemplate.execute(redisScript, List.of("cset", "ccnt"));
        if (result == null || result .size() <=1) {
            throw new RuntimeException("get cache failed");
        }
        Long status = (Long) result.get(0);
        if (status == 1) {
            return (List<Integer>) result.get(1);
        } else {
            throw new RuntimeException("no cache");
        }
    }

    public List<Integer> getOrCreate() {
        try {
            return get();
        } catch (RuntimeException e) {
            log.error("no cache to get",e);
            create(List.of(1,2,3,4,5));
            return get();
        }
    }

    public void update(Integer value) {
        String script = "if redis.call('exists', KEYS[2]) == 1 " +
                "then redis.call('incr', KEYS[2]); redis.call('sadd', KEYS[1], ARGV[1]) " + "else return nil " + "end";
        RedisScript<List<Integer>> redisScript = new DefaultRedisScript<>(script);
        redisTemplate.execute(redisScript, List.of("cset", "ccnt"), value);
    }


    public void create(List<Integer> cacheContent) {
        String createCacheScript;
        try (InputStream is = LettuceCacheService.class.getResourceAsStream("/lua/create_cache.lua")) {
            Objects.requireNonNull(is);
            createCacheScript = IOUtils.toString(is, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("get lua script failed", e);
        }

        RedisScript<Long> redisScript = new DefaultRedisScript<>(createCacheScript,Long.class);
        Long result = redisTemplate.execute(redisScript, List.of("cset", "ccnt"), cacheContent.toArray());

        if (result != 1) {
            throw new RuntimeException("create cache failed with status: " + result);
        }
    }


}
