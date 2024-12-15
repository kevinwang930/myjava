package kevin.project.encryption;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class OpensslLearn {

    public static String keyGenerationLearn(int bSize) throws IOException, InterruptedException {
        Process process = new ProcessBuilder("openssl", "rand", "-hex", String.valueOf(bSize)).start();
        InputStream is = process.getInputStream();
        byte[] result = is.readAllBytes();
        process.waitFor();
        byte[] key = new byte[bSize*2];
        System.arraycopy(result, 0, key, 0, bSize*2);
        // Optionally, print the key in a readable format
        String keyStr = new String(key);
        System.out.println("Key (Hex): " + keyStr);

        return keyStr;
    }

    public static void encryptLearn() throws IOException, InterruptedException {

        String key = keyGenerationLearn(32);
        String iv = keyGenerationLearn(16);
        Process process = new ProcessBuilder("openssl", "enc",
                "-aes-256-cbc",
                "-nosalt",
                "-in", "/Users/hwkf-marlsen-47932/Downloads/sample_2560x1440.mp4",
                "-out", "/Users/hwkf-marlsen-47932/Downloads/encrypt.mp4",
                "-K", key,
                "-iv", iv
        )
                .start();
        process.waitFor();
        System.out.println(IOUtils.toString(process.getErrorStream()));
        process = new ProcessBuilder("openssl", "enc", "-d",
                "-aes-256-cbc",
                "-nosalt",
                "-K", key,
                "-iv", iv,
                "-in", "/Users/hwkf-marlsen-47932/Downloads/encrypt.mp4", "-out", "/Users/hwkf-marlsen-47932/Downloads/decrypt.mp4").start();
        process.waitFor();
        System.out.println(IOUtils.toString(process.getErrorStream()));
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        encryptLearn();
    }
}
