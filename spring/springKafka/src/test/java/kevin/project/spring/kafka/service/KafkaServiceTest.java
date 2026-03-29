package kevin.project.spring.kafka.service;

import kevin.project.spring.kafka.model.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(properties = {
    "spring.kafka.bootstrap-servers=localhost:9092",
    "logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%thread] %logger{36}:%line - %msg%n"
})
@Slf4j
class KafkaServiceTest {


    @Autowired
    private KafkaService kafkaService;

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
}
