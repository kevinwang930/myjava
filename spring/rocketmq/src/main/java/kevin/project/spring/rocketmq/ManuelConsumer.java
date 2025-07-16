package kevin.project.spring.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.apache.rocketmq.spring.support.RocketMQConsumerLifecycleListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic = "TOPIC_TEST", consumerGroup = "GROUP_TEST2")
public class ManuelConsumer
        implements
        RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(String message) {
        log.info("message received: {}", message);
    }


    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // Set the consumer to MANUAL ACKNOWLEDGMENT mode
        consumer.setMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            try {
                msgs.forEach(msg -> {
                    String message = new String(msg.getBody());
                    System.out.println("Processing message: " + message);
                    onMessage(message);
                    // Add your custom logic here
                });

                // After successful processing, commit the offset
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                // Requeue the message for reprocessing
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
    }
}
