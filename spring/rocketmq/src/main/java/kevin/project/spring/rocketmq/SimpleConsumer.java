package kevin.project.spring.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic = "TOPIC_TEST", consumerGroup = "GROUP_TEST")
public class SimpleConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("message received: {}", message);
    }
}
