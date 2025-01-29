package kevin.project.encryption;

import cn.hutool.crypto.digest.DigestUtil;

import java.security.MessageDigest;

public class Md5Learn {

    public static void digest(String content) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] digest = md5.digest(content.getBytes());
        for (byte b : digest) {
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
    }

    public static void hutuDigest(String content) {
        System.out.println(DigestUtil.md5Hex(content));
    }

    public static void main(String[] args) {

        Long standardMatchId = 4050017L;
        String dataSourceCode = "AO";
        String standardCategoryId = "341";
        String content = "Ronghe:StandardCategoryMarketData:" + standardMatchId + "_" + dataSourceCode + "_" +
                standardCategoryId;
        // digest(content);
        hutuDigest(content);

    }


}
