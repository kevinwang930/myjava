package kevin.project.config;

import lombok.Data;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @version 1.0
 * @ClassName StreamConfig
 * @Description TODO
 * @Date 2024/7/24
 **/
@Component
@ConfigurationProperties(prefix = "stream")
@Data
public class StreamConfig {

    private String scheme;

    private String host;

    private String path;

    private String secretKey;

    private String accessKey;

    private String footballPath;

    public URI getFootballUrl() throws URISyntaxException {
        return new URIBuilder().setScheme(scheme).setHost(host).setPath(footballPath.trim()).build();
    }
}
