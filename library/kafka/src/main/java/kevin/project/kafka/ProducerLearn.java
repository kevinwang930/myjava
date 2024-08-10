package kevin.project.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @version 1.0
 * @ClassName Producer
 * @Description TODO
 * @Date 6/27/24
 **/
public class ProducerLearn {


    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("producer started");
        for (int i = 0; i < 5; i++) {

            producer.send(new ProducerRecord<>("quickstart-events", "key"+i, "value"));
            System.out.println("key_" +i + " sent "+ System.currentTimeMillis());
            Thread.sleep(1000);
        }
        producer.close();
    }
}
