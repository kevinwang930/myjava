package kevin.project.object;

import java.util.Base64;
import java.util.HexFormat;

public class StringLearn {

    public static void replaceLearn() {
        String a = "[\"/_lucky_/file/download?fileName=2024/04/20240429151350681o90.56.04.png\"]";
        System.out.println(a.replaceAll("/_lucky_/file/download\\?fileName=","test/"));
    }

    public static void base64Learn() {
        byte[] bytes = new byte[16];
        Base64.Encoder encoder = Base64.getUrlEncoder();
        System.out.println(encoder.encodeToString(bytes));
    }

    public static void hexToBase64(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        System.out.println(Base64.getEncoder().encodeToString(data));
    }

    public static void base64ToHex(String base64) {

        byte[] bytes = Base64.getDecoder().decode(base64);
        String hex = HexFormat.of().formatHex(bytes);
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        System.out.println(Base64.getEncoder().encodeToString(data));
    }

    public static void main(String[] args) {
//        replaceLearn();
        base64Learn();
        hexToBase64("420f8a835bb48ebeb845b0a26c5aa608");
        hexToBase64("e0062ad0842b6a43a79d02cdd156d589");
    }
}
