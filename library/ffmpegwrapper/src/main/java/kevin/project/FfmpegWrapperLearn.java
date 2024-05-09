package kevin.project;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class FfmpegWrapperLearn {

    public static void transcodeLearn() throws IOException {
        FFmpeg ffmpeg = new FFmpeg("/opt/homebrew/bin/ffmpeg");
        FFprobe ffprobe = new FFprobe("/opt/homebrew/bin/ffprobe");
        String inputPath = FfmpegWrapperLearn.class.getResource("/video_sample1.mp4").getFile().toString();
        FFmpegBuilder builder = new FFmpegBuilder()

                .setInput(inputPath)     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists

                .addOutput("output.mp4")   // Filename for the destination
                .setFormat("mp4")        // Format is inferred from filename, or can be set
                .disableSubtitle()       // No subtiles

                .setAudioChannels(1)         // Mono audio
                .setAudioCodec("aac")        // using the aac codec
                .setAudioSampleRate(48_000)  // at 48KHz
                .setAudioBitRate(32768)      // at 32 kbit/s

                .setVideoCodec("libx264")     // Video using x264
                .setVideoFrameRate(24, 1)     // at 24 frames per second
                .setVideoResolution(640, 480) // at 640x480 resolution

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

// Run a one-pass encode
        executor.createJob(builder).run();

// Or run a two-pass encode (which is better quality at the cost of being slower)
//        executor.createTwoPassJob(builder).run();

    }

    public static void m3u8Learn() throws IOException {
        FFmpeg ffmpeg = new FFmpeg("/opt/homebrew/bin/ffmpeg");
        FFprobe ffprobe = new FFprobe("/opt/homebrew/bin/ffprobe");
        String inputPath = FfmpegWrapperLearn.class.getResource("/video_sample1.mp4").getFile().toString();
        FFmpegBuilder builder = new FFmpegBuilder()

                .setInput(inputPath)     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists
                .addOutput("test1.m3u8")   // Filename for the destination
                .setFormat("hls")        // Format is inferred from filename, or can be set

//                .setVideoCodec("libx264")     // Video using x264
//                .setVideoFrameRate(24, 1)     // at 24 frames per second
//                .setVideoResolution(640, 480) // at 640x480 resolution

//                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .addExtraArgs("-start_number","0")
                .addExtraArgs("-hls_time","10")
                .addExtraArgs("-hls_list_size","0")
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

// Run a one-pass encode
        executor.createJob(builder).run();
    }


    public static void main(String[] args) throws IOException {
        m3u8Learn();
    }
}