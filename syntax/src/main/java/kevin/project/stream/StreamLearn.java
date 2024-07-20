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
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void scannerLearn() {
        InputStream in = this.getClass().getResourceAsStream("/test.txt");
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    public void bufferedReaderLearn() {
        InputStream in = this.getClass().getResourceAsStream("/test.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        bufferedReader.lines().forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        StreamLearn streamLearn = new StreamLearn();
//        streamLearn.inputOutputLearn();
//        streamLearn.fileInputOutputLearn();
//        streamLearn.streamLearn();
        streamLearn.streamExceptionLearn();
    }

    public void streamExceptionLearn() throws IOException {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> parallel = integerStream
                .parallel();
        Stream<Integer> integerStream1 = parallel.map(num -> {
            if (num == 3) {
                throw new RuntimeException("runtime exception");
            }
            return num;
        });
        List<Integer> list = integerStream1.collect(Collectors.toList());
        System.out.println(list);
    }

    public void streamLearn() {
        List<Integer> numList = Stream.of(1, 2, 3, 4, 5)
                .parallel().
                map(number -> {
                    try {

                        int i = new Random().nextInt(20);
                        System.out.println("start sleep " + i);
                        Thread.sleep(i * 1000);
                        System.out.println("finish sleep");
                        return number;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return number;
                    }
                }).collect(Collectors.toList());
        System.out.println(numList);
    }
}
