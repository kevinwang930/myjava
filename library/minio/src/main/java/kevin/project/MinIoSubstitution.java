package kevin.project;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MinIoSubstitution {


    private static  String ENDPOINT = "https://s3.amazonaws.com"; // Your MinIO server URL
    private static  String ACCESS_KEY = ""; // Your access key
    private static  String SECRET_KEY = ""; // Your secret key
    private static final String BUCKET_NAME = "newgs"; // Your bucket name

    private MinioClient minioClient = MinioClient.builder()
            .endpoint(ENDPOINT)
            .credentials(ACCESS_KEY, SECRET_KEY)
            .build();

    public  void m3u8ApiSubstitute() throws Exception {


        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(BUCKET_NAME).
                        recursive(true).
                        build());

        for (Result<Item> result : results) {
            Item item = result.get();
            String key = item.objectName();
            if (key.endsWith("video.m3u8")) {
                // Download the file
                InputStream stream = minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(BUCKET_NAME)
                                .object(key)
                                .build());
                String content = IOUtils.toString(stream,StandardCharsets.UTF_8);
                stream.close();
                String toReplace = "http://190.92.230.245:8080";
                if (content.contains(toReplace)) {
                    String replaced = content.replace(toReplace, "https://api.vogsfbt.xyz");
                    System.out.println(replaced);
                    System.out.println(key);

                    ByteArrayInputStream byteStream = new ByteArrayInputStream(replaced.getBytes(StandardCharsets.UTF_8));
                    PutObjectArgs putArgs = PutObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(key)
                            .stream(byteStream, byteStream.available(), -1)
                            .contentType("application/x-mpegURL")
                            .build();
                    minioClient.putObject(putArgs);
                    byteStream.close();
                }
            }
        }
    }

    public void statObject() throws Exception {
        try {

            StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs.builder().bucket(BUCKET_NAME)
                    .object("test/123.mp4")
                    .build());
            System.out.println(statObjectResponse.toString());
        } catch (MinioException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        MinIoSubstitution minIoSubstitution = new MinIoSubstitution();
        minIoSubstitution.statObject();
    }
}
