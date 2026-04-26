package kevin.project.spring.kafka.service;

import kevin.project.spring.kafka.model.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareRebalanceListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class KafkaServiceTest {

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ConsumerFactory<String,Object> consumerFactory;

    @Test
    void sendMessage() throws InterruptedException {
        CountDownLatch rebalanceLatch = new CountDownLatch(1);

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-consumer-" + System.currentTimeMillis());
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", JacksonJsonDeserializer.class);
        props.put("auto.offset.reset", "latest");
        props.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, "kevin.project.spring.kafka.model.KafkaMessage");

        KafkaConsumer<String, KafkaMessage> consumer = new KafkaConsumer<>(props);

        // Subscribe with rebalance listener
        consumer.subscribe(Collections.singletonList("kafka.test"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                log.info("Partitions assigned: {}", partitions);
                rebalanceLatch.countDown();
            }
        });

        // Poll with longer timeout to trigger rebalancing
        while (rebalanceLatch.getCount() > 0) {
            consumer.poll(Duration.ofMillis(500));
        }

        // Extra safety wait for rebalance to complete
        assertTrue(rebalanceLatch.await(1, TimeUnit.SECONDS), "Rebalance did not complete");

        // Print offset after rebalance
        consumer.assignment().forEach(partition -> {
            long position = consumer.position(partition);
            log.info("Partition: {}, Position after rebalance: {}", partition, position);
        });

        KafkaMessage kafkaMessage = new KafkaMessage("test-id", "test-content", System.currentTimeMillis());
        kafkaService.sendMessage("kafka.test", kafkaMessage);

        ConsumerRecords<String, KafkaMessage> records = consumer.poll(Duration.ofSeconds(10));

        // Print offset after poll (before commit)
        consumer.assignment().forEach(partition -> {
            long position = consumer.position(partition);
            log.info("Partition: {}, Position after poll (before commit): {}", partition, position);
        });

        assertFalse(records.isEmpty(), "No records received from Kafka");

        Map<TopicPartition, OffsetAndMetadata> committedOffsets = consumer.committed(consumer.assignment());
        log.info("Committed offsets map: {}", committedOffsets);
        // Explicitly commit the offset
        consumer.commitSync();

        // Print offset after commit
        consumer.assignment().forEach(partition -> {
            long position = consumer.position(partition);
            log.info("Partition: {}, Position: {}", partition, position);
        });

        committedOffsets = consumer.committed(consumer.assignment());
        log.info("Committed offsets map: {}", committedOffsets);


        assertFalse(records.isEmpty(), "No records received from Kafka");

        KafkaMessage received = records.iterator().next().value();
        log.info("Received message content: {}", received.getContent());

        assertNotNull(received);
        assertEquals("test-content", received.getContent());
        assertEquals("test-id", received.getId());

        consumer.close();
    }

    @Test
    void listenerContainerTest() throws InterruptedException {
        CountDownLatch assignmentLatch = new CountDownLatch(1);
        CountDownLatch messageLatch = new CountDownLatch(1);
        List<KafkaMessage> received = new ArrayList<>();

        // ContainerProperties bundles the topic + all listener/container-level config —
        // the exact same object @KafkaListener builds under the hood.
        ContainerProperties containerProps = new ContainerProperties("kafka.test");

        // Programmatic equivalent of the @KafkaListener method body.
        // MessageListener<K,V> = single-record, fire-and-forget (no Acknowledgment param).
        // Use AcknowledgingMessageListener if you need manual ack control.
        containerProps.setMessageListener((MessageListener<String, KafkaMessage>) record -> {
            log.info("Container received: topic={}, partition={}, offset={}, key={}, value={}",
                    record.topic(), record.partition(), record.offset(), record.key(), record.value());
            received.add(record.value());
            messageLatch.countDown();
        });

        // ConsumerAwareRebalanceListener exposes the raw Consumer at rebalance time,
        // so we can inspect (or seek) exact offsets right after partitions are assigned.
        containerProps.setConsumerRebalanceListener(new ConsumerAwareRebalanceListener() {
            @Override
            public void onPartitionsAssigned(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
                partitions.forEach(tp ->
                        log.info("Partition assigned: {}, current position: {}", tp, consumer.position(tp)));
                assignmentLatch.countDown();
            }
        });

        // KafkaMessageListenerContainer = the single-threaded container that @KafkaListener uses.
        // ConcurrentKafkaListenerContainerFactory wraps N of these for concurrency > 1.
        KafkaMessageListenerContainer<String, KafkaMessage> container =
                new KafkaMessageListenerContainer<>(consumerFactory, containerProps);
        container.setBeanName("test-listener-container");
        container.start();

        // Block until Kafka finishes the rebalance and the container is actively polling.
        // Sending before this point risks the message landing before "latest" is snapped.
        assertTrue(assignmentLatch.await(15, TimeUnit.SECONDS), "Partition assignment timed out");
        log.info("Container running: {}, assigned partitions: {}",
                container.isRunning(), container.getAssignedPartitions());

        KafkaMessage msg = new KafkaMessage("container-id", "container-content", System.currentTimeMillis());
        kafkaService.sendMessage("kafka.test", msg);

        assertTrue(messageLatch.await(10, TimeUnit.SECONDS), "Listener did not receive the message in time");

        assertFalse(received.isEmpty());
        assertEquals("container-id", received.get(0).getId());
        assertEquals("container-content", received.get(0).getContent());

        container.stop();
    }
}
