package kevin.project;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class OtherClass {
    public static void staticHello() {
        System.out.println("static hello from other class");
    }

    public void hello() throws URISyntaxException {
        ProtectionDomain pd = this.getClass().getProtectionDomain();
        CodeSource cs = pd.getCodeSource();
        URL url = cs.getLocation();
        File file = new File(url.toURI());
        String jarPath = file.getAbsolutePath();
        System.out.println("JAR path: " + jarPath);
        System.out.println(StringUtils.capitalize("hello"));
        System.out.println("hello from other class");
    }
}
