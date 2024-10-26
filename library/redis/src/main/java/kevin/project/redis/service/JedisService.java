package kevin.project.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @ClassName RedisService
 * @Description TODO
 * @Date 7/6/24
 **/
@Service
public class JedisService {
    @Autowired
    private JedisPool jedisPool;

    public void setKey(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        }
    }

    public String getKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    public List<String> getAndClearList(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            String script = "local val = redis.call('LRANGE', KEYS[1], 0, -1); " +
                    "redis.call('DEL', KEYS[1]); " +
                    "return val;";
            return (List<String>) jedis.eval(script, 1, key);
        }
    }

    public Set<String> keys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("*");
        }
    }

    public void getList(String key) {
        String script = "local val = redis.call('LRANGE', KEYS[1], 0, -1); " +
                "return val;";
        try (Jedis jedis = jedisPool.getResource()) {
            System.out.println(jedis.eval(script,1,key)) ;
        }
    }


}
