package kevin.project.encryptionLearn;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.util.Arrays;

public class AESLearn {


    public static void openSSLDecrypt() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Path folderPath = Paths.get("/Users/hwkf-marlsen-47932/Downloads");

        // Read the encryption key (ensure it's exactly 16 bytes for AES-128)
        byte[] key = hexStringToByteArray("63ccb45a6fb711fd5c5a7492ede6d7c0");
        if (key.length != 16) {
            throw new IllegalArgumentException("The encryption key must be exactly 16 bytes (128 bits) for AES-128.");
        }

        // Read the encrypted data
        byte[] encryptedData = Files.readAllBytes(folderPath.resolve("encrypt.mp4"));

        // Initialize an empty IV byte array
        byte[] iv = new byte[16];

        // Check if the file has the "Salted__" header and extract the IV

        // Extract the salt and IV from the file
//        byte[] salt = Arrays.copyOfRange(encryptedData, 8, 16);
//        iv = Arrays.copyOfRange(encryptedData, 16, 32);

        // Extract the ciphertext data
//        byte[] ciphertext = Arrays.copyOfRange(encryptedData, 32, encryptedData.length);

        // Decrypt the data
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decryptedData = cipher.doFinal(encryptedData);

        // Write the decrypted data to a file
        Files.write(folderPath.resolve("decrypted.mp4"), decryptedData);

        System.out.println("Decryption complete. File saved as decrypted.mp4");

    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }


    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        openSSLDecrypt();
    }


}
