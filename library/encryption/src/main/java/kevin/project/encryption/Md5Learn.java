package kevin.project.encryption;

import java.security.MessageDigest;

public class Md5Learn {

    public static void digest() {
        String test = "test124345";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] digest = md5.digest(test.getBytes());
        for (byte b : digest) {
            System.out.print(Integer.toHexString(b & 0xff));
        }
    }

    public static void main(String[] args) {
        digest();
    }

    
}
