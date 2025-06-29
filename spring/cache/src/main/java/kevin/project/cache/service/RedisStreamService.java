package kevin.project.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RedisStreamService  {

    private static final String STREAM_KEY = "number-stream";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        startStreamConsumer();
    }

    public void addNumberToStream(Integer number) {
        Map<String, String> messageBody = Map.of("number", String.valueOf(number));
        redisTemplate.opsForStream()
                     .add(STREAM_KEY, messageBody);
        log.info("Added number {} to stream", number);
    }

    private void startStreamConsumer() {
        Thread consumerThread = new Thread(() -> {
            String groupName = "numberGroup";
            String consumerName = "consumer1";
            try {
                redisTemplate.opsForStream()
                             .createGroup(STREAM_KEY, groupName);
            } catch (Exception e) {
                log.info("Group {} already exists", groupName);
            }

            while (true) {
                try {
                    List<MapRecord<String, Object, Object>> messages = redisTemplate.opsForStream()
                                                                                    .read(Consumer.from(groupName,
                                                                                                    consumerName),
                                                                                            StreamReadOptions.empty()
                                                                                                             .block(Duration.ofSeconds(
                                                                                                                     10))
                                                                                                             .count(1),
                                                                                            StreamOffset.create(
                                                                                                    STREAM_KEY,
                                                                                                    ReadOffset.lastConsumed()));

                    if (CollectionUtils.isNotEmpty(messages)) {
                        messages.forEach(message -> {
                            log.info("Consumed number: {}", message.getValue()
                                                                   .get("number"));
                            redisTemplate.opsForStream()
                                         .acknowledge(STREAM_KEY, groupName, message.getId());
                        });
                    }
                } catch (Exception e) {
                    log.error("Stream consumer interrupted", e);
                    Thread.currentThread()
                          .interrupt();
                    break;
                }
            }
        });
        consumerThread.start();
    }




}
