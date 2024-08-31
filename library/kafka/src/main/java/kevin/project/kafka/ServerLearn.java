package kevin.project.kafka;

import kafka.Kafka$;

/**
 * @version 1.0
 * @ClassName ServerLearn
 * @Description TODO
 * @Date 8/28/24
 **/
public class ServerLearn {


    public static void main(String[] args) {
        // Prepare the arguments
        String[] kafkaArgs = {"/opt/homebrew/etc/kafka/kraft/server.properties"};

        // Call Kafka's main method
        Kafka$.MODULE$.main(kafkaArgs);
    }
}
