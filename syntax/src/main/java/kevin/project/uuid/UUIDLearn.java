package kevin.project.uuid;

import java.util.Base64;
import java.util.UUID;

public class UUIDLearn {

    public static byte[] hex2Bytes(String hex) {
        if (hex == null || hex.isEmpty()) {
            return new byte[0];
        }
        byte[] bytes = hex.getBytes();
        int n = bytes.length >> 1;
        byte[] buf = new byte[n];
        for (int i = 0; i < n; i++) {
            int index = i << 1;
            buf[i] = (byte) ((byte2Int(bytes[index]) << 4) | byte2Int(bytes[index + 1]));
        }
        return buf;
    }

    private static int byte2Int(byte b) {
        return (b <= '9') ? b - '0' : b - 'a' + 10;
    }

    public static String compressUUID(String uuid){
        String hex = uuid.replace("-", "");
        byte[] bytes = hex2Bytes(hex);
        return Base64.getUrlEncoder().withoutPadding(). encodeToString(bytes);
    }

    public static void uuidLearn() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
        byte[] bytes = hex2Bytes(uuid);
        String result = Base64.getEncoder().withoutPadding(). encodeToString(bytes);
        System.out.println(result);
    }

    public static void main(String[] args) {
        uuidLearn();
    }
}
