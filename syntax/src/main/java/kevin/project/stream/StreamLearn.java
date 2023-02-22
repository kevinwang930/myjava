package kevin.project.stream;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class StreamLearn {
    private List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);

    public void inputOutputLearn() throws IOException {
        InputStream in = this.getClass().getResourceAsStream("/test.txt");
        Path target = Paths.get("output.txt");
        OutputStream out = Files.newOutputStream(target, StandardOpenOption.TRUNCATE_EXISTING);
//        Files.copy(in,target, StandardCopyOption.REPLACE_EXISTING);
        IOUtils.copy(in, out);
    }

    public void fileInputOutputLearn() throws IOException {
        InputStream in = this.getClass().getResourceAsStream("/test.txt");
        byte[] first5character = new byte[10];
        in.read(first5character);
        String content = new String(first5character, StandardCharsets.UTF_8);
        System.out.println(content);
//        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

//        fileOutputStream.write();
    }

    public static void main(String[] args) throws IOException {
        StreamLearn streamLearn = new StreamLearn();
        streamLearn.inputOutputLearn();
        streamLearn.fileInputOutputLearn();
    }
}
