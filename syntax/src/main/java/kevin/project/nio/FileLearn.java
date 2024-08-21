package kevin.project.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

/**
 * @version 1.0
 * @ClassName FileLearn
 * @Description TODO
 * @Date 2024/8/18
 **/
public class FileLearn {

    public static void createFileLearn() throws IOException {
        Path path = Paths.get("test.log");
        Files.deleteIfExists(path);
        Files.createFile(path);
        System.out.println(Files.exists(path));
        Files.delete(path);
        File file = new File("test.log");
        System.out.println(file.exists());
        file.createNewFile();
        System.out.println(file.exists());
        file.delete();
        System.out.println(file.exists());





    }


    public static void main(String[] args) throws IOException {
        createFileLearn();
    }
}
