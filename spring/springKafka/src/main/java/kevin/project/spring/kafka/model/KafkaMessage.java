package kevin.project.spring.kafka.model;

/**
 * @version 1.0
 * @ClassName KafkaMessage
 * @Description TODO
 * @Date 2/8/26
 **/
public class KafkaMessage {
    private String id;
    private String content;
    private long timestamp;

    public KafkaMessage() {
    }

    public KafkaMessage(String id, String content, long timestamp) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
