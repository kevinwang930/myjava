package kevin.project.service;

import kevin.project.config.StreamConfig;
import org.apache.commons.lang3.SystemUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @version 1.0
 * @ClassName StreamService
 * @Description TODO
 * @Date 2024/7/24
 **/
@Service
public class StreamService {


    @Autowired
    private StreamConfig streamConfig;

    private Logger logger = LogManager.getLogger();

    public String getFootballMatchInfo() {
        try {
            URI footballUrl = streamConfig.getFootballUrl();
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(footballUrl);
                long currentMills = System.currentTimeMillis();
                request.addHeader("Sp-Access-Key",streamConfig.getAccessKey());
                request.addHeader("Sp-Acess-Time",currentMills);
                String secret = streamConfig.getSecretKey() + "|" + currentMills + "|" + streamConfig.getFootballPath();
                String digest = md5(secret);
                request.addHeader("Sp-Access-Key-Secret",digest);
                request.addHeader("Accept", "application/json");

                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        return EntityUtils.toString(entity);
                    }
                }
            } catch (Exception e) {
                logger.error(e);
            }
        } catch (URISyntaxException e) {
            logger.error(e);
        }
        return "";
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
