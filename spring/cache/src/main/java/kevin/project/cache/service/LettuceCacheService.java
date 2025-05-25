package kevin.project.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
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

    public Long testScript() {
        Long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        return redisTemplate.execute(defaultScript, List.of("12345"),  System.currentTimeMillis(),
                64700000000L);
    }

    public void testString(String content) {
        redisTemplate.opsForValue().set("test", content);
    }
}
