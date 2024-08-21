package kevin.project.javacv;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @ClassName FfmpegLearn
 * @Description TODO
 * @Date 2024/8/18
 **/
public class FfmpegLearn {

    public static void probeLearn() {
        List<String> command = new ArrayList<>();
        command.add("ffprobe");
        command.add("-v");
        command.add("quiet");
        command.add("-print_format");
        command.add("json");
        command.add("-show_format");
        command.add("-show_streams");
        command.add("/Users/hwkf-marlsen-47932/Documents/sample.mp4");

        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                String out = output.toString();
                System.out.println(out);
                parseFFprobeOutput(out);
            } else {
                System.err.println("FFprobe process failed with exit code: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseFFprobeOutput(String jsonOutput) {
        JSONObject probeResult = JSON.parseObject(jsonOutput);
        if (probeResult != null) {
            // Access video stream info
            List<JSONObject> streams = probeResult.getJSONArray("streams").toList(JSONObject.class);
            for (JSONObject stream : streams) {
                String codecType = stream.getString("codec_type");
                String codecName = stream.getString("codec_name");
                System.out.println(codecType + " Encoder: " + codecName);
            }

            // Access format info
            JSONObject format = probeResult.getJSONObject("format");
            System.out.println("Format Name: " + format.getString("format_name"));
            System.out.println("Duration: " + format.getString("duration"));
        }
    }



    public static void main(String[] args) {
        probeLearn();
    }
}
