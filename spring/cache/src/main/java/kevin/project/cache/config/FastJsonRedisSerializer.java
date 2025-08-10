package kevin.project.cache.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class FastJsonRedisSerializer implements RedisSerializer<Object> {

    private static final byte[] EMPTY_ARRAY = new byte[0];

    public static final FastJsonRedisSerializer INSTANCE = new FastJsonRedisSerializer();



    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return EMPTY_ARRAY;
        }
        try {
            // For Fastjson v1
            return JSON.toJSONString(object, JSONWriter.Feature.WriteClassName).getBytes();

            // For Fastjson v2, use this instead:
            // return JSON.toJSONString(object, JSONWriter.Feature.WriteClassName).getBytes();
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            String str = new String(bytes);
             return JSON.parse(str, JSONReader.Feature.SupportAutoType);
        } catch (Exception ex) {
            throw new SerializationException("Could not deserialize: " + ex.getMessage(), ex);
        }
    }

}
