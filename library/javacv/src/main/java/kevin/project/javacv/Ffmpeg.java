package kevin.project.javacv;

import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.opencv.presets.opencv_core;

import java.net.URISyntaxException;
import java.net.URL;

/**
 * @version 1.0
 * @ClassName Ffmpeg
 * @Description TODO
 * @Date 2024/8/10
 **/
public class Ffmpeg {

    public static void detectFormat() throws URISyntaxException, FrameGrabber.Exception {
        System.setProperty("org.bytedeco.javacpp.pathsFirst","true");
        System.setProperty("org.bytedeco.javacpp.logger.debug","true");
        URL path = Ffmpeg.class.getResource("/sample.mp4");
        System.out.println(path);

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(path)) {
            grabber.start();
            System.out.println(grabber.getVideoCodecName());
            grabber.stop();
        }
    }


    public static void main(String[] args) throws URISyntaxException, FrameGrabber.Exception {
        detectFormat();
    }

}
