package kevin.project.object;

import java.util.Base64;
import java.util.HexFormat;

public class StringLearn {

    public static void replaceLearn() {
        String a = "[\"/_lucky_/file/download?fileName=2024/04/20240429151350681o90.56.04.png\"]";
        System.out.println(a.replaceAll("/_lucky_/file/download\\?fileName=","test/"));
    }

    public static void formatLearn() {
        String a = "1234567890";
        System.out.println(String.format("123 {%s}", Integer.parseInt(a)));
    }

    public static void eqaulsLearn() {
        String a = "1234567890";
        String b = "1234567890";
        String c = new String("1234567890");
        System.out.println(a == b);
        System.out.println(a == c);
    }

    public static void intToString() {
        long a = 100l;
        Object b =a ;
        System.out.println(b.toString());
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
        System.out.println(hex);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase(); // Convert to upper-case for consistency
    }

    public static void main(String[] args) {
//        replaceLearn();
//        formatLearn();
//        eqaulsLearn();
        //base64Learn();
        intToString();
//        hexToBase64("420f8a835bb48ebeb845b0a26c5aa608");
//        hexToBase64("e0062ad0842b6a43a79d02cdd156d589");
//        base64ToHex("r/Qrql47hG4znwkeqW53ww==");
//        base64ToHex("AAAAAAAAAAAAAAAAAAAAAA==");
    }
}
