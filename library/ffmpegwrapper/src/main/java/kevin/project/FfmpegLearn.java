package kevin.project;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FfmpegLearn {


    public static void main(String[] args) {
        outputstreamLearn();
    }

    public static void outputstreamLearn() {

        try (InputStream inputStream = Files.newInputStream(Path.of("src/main/resources/video_sample1.mp4"))) {
            String ffmpegCmd = "ffmpeg -i - -hls_time 10 -start_number 0 -hls_list_size 0 " +
                    "-f hls output.m3u8";
            ProcessBuilder builder = new ProcessBuilder(
                    "ffmpeg",
                    "-i", "-",  // Read from stdin
                    "-hls_time", "10",
                    "-start_number", "0",
                    "-hls_list_size", "0",
                    "-f", "hls",
                    "output.m3u8"
            );
            builder.redirectErrorStream(false);
            Process process = builder.start();
            OutputStream input = process.getOutputStream();
            inputStream.transferTo(input);
            input.close();
            InputStream output = process.getInputStream();
            System.out.println(IOUtils.toString(output));
            InputStream errput = process.getErrorStream();
            System.out.println(IOUtils.toString(errput));

            process.waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
