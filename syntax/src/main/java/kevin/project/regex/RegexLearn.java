package kevin.project.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @ClassName RegexLearn
 * @Description TODO
 * @Date 6/22/24
 **/
public class RegexLearn {

    public static void regexSub(String[] args) {
        String urlString = "https://test-guashen.s3.ap-east-1.amazonaws.com/user/avatar/1585549910/sample1719046118.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIASSBJILF72TEPPBQ5%2F20240622%2Fap-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240622T113706Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=3727fe6f8d200fba899acf3a6c4d2c9abb1cb1d895ab4e4c0ab39f74be497562";
        String newUrl = urlString.replaceFirst("^https://.*s3\\..*\\.amazonaws\\.com", "dvbgs.cn");
        System.out.println(newUrl);
    }

    public static void regexParam(String[] args) {
        String urlString = "https://api.vogsfbt.xyz/melon-video/artifacts/v1/m3u8?id=NW-HTIQQTLqy5dhbIenRng";
        String pattern = "^.*id=(?<id>[^&]{22})";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(urlString);
        if (matcher.find()) {
            System.out.println(matcher.group("id"));
        }
        System.out.println(urlString.matches(pattern)) ;
    }

    public static void main(String[] args) {
        regexSub(args);
        regexParam(args);
    }
}
