package kevin.project;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class HttpClient {
    public static void main(String[] args) throws Exception {

        // Create an HttpClient instance
        try (var httpClient = HttpClients.createDefault()) {

            // Create an HttpGet request with the URL you want to call
            var httpGet = new HttpGet("http://baidu.com");

            // Execute the request and get the response
            CloseableHttpResponse response = httpClient.execute(httpGet);

            // Get the response body as a string
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);

            // Print the response body to the console
            System.out.println(responseBody);
        }
    }
}
