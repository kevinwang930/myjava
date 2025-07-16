package kevin.project.spring.rocketmq;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic = "TOPIC_TEST", consumerGroup = "GROUP_TEST1", consumeMode = ConsumeMode.ORDERLY)
public class OrderlyConsumer implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        String message1 = new String(message.getBody());
        log.info("message received: {}", message1);
        if (message1.equals("error") && message.getReconsumeTimes() < 5) {
            throw new RuntimeException("this is exception");
        }
    }
}
