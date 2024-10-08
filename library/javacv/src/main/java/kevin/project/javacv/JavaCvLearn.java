package kevin.project.javacv;

import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bytedeco.ffmpeg.global.avutil;

/**
 * @version 1.0
 * @ClassName Ffmpeg
 * @Description TODO
 * @Date 2024/8/10
 **/
public class JavaCvLearn {

    public static void detectFormat() throws URISyntaxException, FrameGrabber.Exception {
//        System.setProperty("org.bytedeco.javacpp.pathsFirst","true");
//        System.setProperty("org.bytedeco.javacpp.logger.debug","true");
        URL path = JavaCvLearn.class.getResource("/sample.mp4");
        System.out.println(path);

        long start = System.currentTimeMillis();
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(path)) {
            grabber.start();
            AVFormatContext formatContext = grabber.getFormatContext();
            System.out.println(formatContext.nb_streams());
            int videoStreamCount = 0;
            for (int i = 0; i < formatContext.nb_streams(); i++) {
                if (formatContext.streams(i).codecpar().codec_type() == avutil.AVMEDIA_TYPE_VIDEO) {
                    videoStreamCount++;
                }
            }
            System.out.println("video stream count: " + videoStreamCount);
            System.out.println(grabber.getVideoCodecName());
            grabber.stop();
            System.out.println("detect cost " + (System.currentTimeMillis() - start));
        }
    }

    public static void encode() throws FrameGrabber.Exception, FFmpegFrameRecorder.Exception {
        String input = "/Users/hwkf-marlsen-47932/Documents/sample-3.mp4";
        String output = "/Users/hwkf-marlsen-47932/Documents/sample-3_compress.mp4";
        FFmpegFrameGrabber grabber = null;
        FFmpegFrameRecorder recorder = null;
        FFmpegLogCallback.setLevel(32);
        FFmpegLogCallback.set();
        try {
            // Initialize the grabber
            grabber = new FFmpegFrameGrabber(input);
            grabber.start();

            // Initialize the recorder
            recorder = new FFmpegFrameRecorder(output, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
            recorder.setVideoQuality(5);
            recorder.setAudioQuality(5);
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
            recorder.start();

            // Read and write frames
            Frame frame;
            while ((frame = grabber.grab()) != null) {
                recorder.record(frame);
            }
        } finally {
            // Close grabber and recorder
            if (grabber != null) {
                grabber.stop();
                grabber.release();
            }
            if (recorder != null) {
                recorder.stop();
                recorder.release();
            }
        }
    }


    public static void segmentJavaCv() throws IOException {
        String path = "/Users/hwkf-marlsen-47932/Documents/sample_30.mp4";
        String output = "/Users/hwkf-marlsen-47932/Documents/javacv/sample_30/output.m3u8";
        Files.createDirectories(Paths.get(output).getParent());
        FFmpegFrameRecorder recorder = null;
        FFmpegLogCallback.set();
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(path)
        ) {
            long start = System.currentTimeMillis();
            System.out.println("start segment " + start);
            grabber.start(true);
            recorder = new FFmpegFrameRecorder(output, grabber.getImageWidth(), grabber.getImageHeight(),
                    grabber.getAudioChannels());

            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setVideoOption("crf", "18");
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);

            recorder.setFormat("hls");
            recorder.setOption("hls_time", "2");
            recorder.setOption("hls_list_size", "0");
            recorder.setOption("start_number", "0");
            recorder.setOption("segment_list","/Users/hwkf-marlsen-47932/Documents/javacv/sample_30/list.log");
            recorder.setOption("hls_flags", "single_file");

            AVFormatContext formatContext = grabber.getFormatContext();


            recorder.start(formatContext);
            AVPacket pkt = null;
            Frame frame = null;
            long dts = 0, pts = 0;
            grabber.flush();
//            while ((frame = grabber.grab()) != null) {
//                recorder.record(frame);
//            }
            while ((pkt = grabber.grabPacket()) != null) {
                recorder.recordPacket(pkt);
            }
            System.out.println("end segment total time " + (System.currentTimeMillis() - start));
        } finally {
            if (recorder != null) {
                recorder.close();
            }
        }
    }

    public static void segmentReEncode() throws IOException {
        String path = "/Users/hwkf-marlsen-47932/Documents/sample_30.mp4";
        String output = "/Users/hwkf-marlsen-47932/Documents/javacv/sample_30/output.m3u8";
        Files.createDirectories(Paths.get(output).getParent());
        FFmpegFrameRecorder recorder = null;
        FFmpegLogCallback.set();

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(path)) {
            long start = System.currentTimeMillis();
            System.out.println("start segment " + start);
            grabber.start();

            recorder = new FFmpegFrameRecorder(output, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());

            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setVideoOption("crf", "30");  // Adjust CRF value as needed
            recorder.setAspectRatio(grabber.getAspectRatio());
            recorder.setFrameRate(grabber.getFrameRate());

            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
            recorder.setAudioBitrate(grabber.getAudioBitrate());
            recorder.setSampleRate(grabber.getSampleRate());
            recorder.setAudioChannels(grabber.getAudioChannels());

            recorder.setFormat("hls");
            recorder.setOption("hls_time", "2");
            recorder.setOption("hls_list_size", "0");
            recorder.setOption("start_number", "0");

            recorder.start();

            Frame frame;
            while ((frame = grabber.grab()) != null) {
                // Here you can process the frame if needed
                // For example, you could resize it:
                // frame = frameResizer.resize(frame);

                recorder.record(frame);
//                if (frame.timestamp / 1000000 % 2 == 0) {
//                    recorder.flush();
//                }
            }

            System.out.println("end segment total time " + (System.currentTimeMillis() - start));
        } finally {
            if (recorder != null) {
                recorder.stop();
                recorder.release();
            }
        }
    }

    public static void segment() throws IOException {
        String path = "/Users/hwkf-marlsen-47932/Documents/sample_30.mp4";
        System.out.println((Files.exists(Paths.get(path)) ? "exist" : "not exist"));
        String output = "/Users/hwkf-marlsen-47932/Documents/ffmpeg/sample_30/output.m3u8";
        Files.createDirectories(Paths.get(output).getParent());
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "ffmpeg",
//                    "-loglevel","40",
                    "-i", path,
                    "-c:v", "copy",
                    "-c:a", "copy",
                    "-crf", "18",
                    "-hls_time", "2",
                    "-start_number", "0",
                    "-hls_list_size", "0",
                    "-f", "hls",
                    "-hls_flags", "single_file",
                    output
            );
            builder.redirectErrorStream(false);
            long start = System.currentTimeMillis();
            System.out.println("start segment " + start);
            Process process = builder.start();
            Thread thread = new Thread(() -> {
                try {

                    InputStream errorStream = process.getErrorStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();


            process.waitFor();
            thread.join();
            System.out.println("segment total time " + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
//        detectFormat();
        segmentJavaCv();
//        segment();
//        segmentReEncode();
//        encode();
    }
}
