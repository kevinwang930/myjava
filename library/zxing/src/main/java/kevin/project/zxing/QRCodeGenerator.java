package kevin.project.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @version 1.0
 * @ClassName QRCodeGenerator
 * @Description TODO
 * @Date 9/29/24
 **/
public class QRCodeGenerator {

    public static void generateQRCode(String text, String filePath, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault()
                               .getPath(filePath);
        MatrixToImageWriter.toBufferedImage(bitMatrix);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }


    public static void main(String[] args) throws IOException, WriterException {
        QRCodeGenerator.generateQRCode("my test","./test.png",512,512);
    }
}
