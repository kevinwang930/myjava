package kevin.project.process;

import org.openjdk.jol.util.IOUtils;

import java.io.IOException;

/**
 * @version 1.0
 * @ClassName ProcessLearn
 * @Description TODO
 * @Date 2024/8/14
 **/
public class ProcessLearn {


    public static void processBuilder() throws IOException {
        Process ffmpeg = new ProcessBuilder("ffmpeg", "--help").start();
        System.out.println(new String(IOUtils.readAllBytes(ffmpeg.getInputStream()))); ;
    }

    public static void main(String[] args) throws IOException {
        processBuilder();
    }
}
