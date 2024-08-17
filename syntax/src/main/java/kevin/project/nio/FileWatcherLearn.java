package kevin.project.nio;

import java.io.IOException;
import java.nio.file.*;

/**
 * @version 1.0
 * @ClassName FileWatcherLearn
 * @Description TODO
 * @Date 2024/8/16
 **/
public class FileWatcherLearn {

    public static void fileWatcherLearn() throws IOException {
        Path targetpath = Paths.get("/Users/hwkf-marlsen-47932/Documents/test.log");
        WatchService watchService = FileSystems.getDefault().newWatchService();
    }


    public static void main(String[] args) {
    }
}
