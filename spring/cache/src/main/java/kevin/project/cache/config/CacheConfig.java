package kevin.project.cache.config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
public class CacheConfig {

    @Primary
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory,
                                          RedisCacheConfiguration cacheConfiguration) {
        return RedisCacheManager.builder(redisConnectionFactory)
                                .cacheDefaults(cacheConfiguration)
                                .build();
    }

    @Bean(name = "localCacheManager")
    public CacheManager localCacheManager() {
        return new ConcurrentMapCacheManager();
    }
}
