package kevin.project;

import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StreamLearn {

    public static void streamLearn() {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(100, TimeUnit.MINUTES)
                .writeTimeout(100, TimeUnit.MINUTES)
                .readTimeout(100, TimeUnit.MINUTES)
                .build();
        File file = new File("/Users/hwkf-marlsen-47932/Downloads/Sintel.mp4");
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                // You can be more specific with the media type depending on your file type
                return MediaType.parse("video/mp4");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                // Stream the file contents directly to the sink provided by OkHttp
                try (Source source = Okio.source(file)) {
                    sink.writeAll(source);
                }
            }

            @Override
            public long contentLength() throws IOException {
                return file.length();  // Return the file length to properly set the Content-Length header
            }
        };

        // Build the request
        Request request = new Request.Builder()
                .url("http://190.92.230.245:8080/melon-video/artifacts/v1/video")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjo4LCJ1c2VyX2tleSI6ImM0MGY3Mjk3LTk3YzktNDQwYS1iNWY2LWQxNGI4MmQ3NjI5MiIsInVzZXJuYW1lIjoidGVzdDEyMyJ9.NGPFFElXxLsrJCq_h3-xF7JLABZC2D09WNbke7v6IBcMhry9RNJ-PFnsEpxxGFa4-SXvIEPHhNIHbskscK4GLA")
                .post(requestBody)
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            System.out.println("Response: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        streamLearn();
    }
}
