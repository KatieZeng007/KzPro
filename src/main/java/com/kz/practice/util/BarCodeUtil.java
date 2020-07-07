package com.kz.practice.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//import org.apache.tomcat.util.codec.binary.Base64;
//import org.apache.commons.codec.binary.Base64;

/**
 * @Description 条形码生成解析
 * @Author KatieZ
 * @Date Created in 14:57  14:57
 */
public class BarCodeUtil {
    /**
     * 获取base64格式条形码
     * @param contents
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static String getBase64Encode(String contents) throws WriterException, IOException {
        String base64 = null;
        BufferedImage bufferedImage = encode(contents);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"jpeg",outputStream);
        outputStream.close();
        base64 = new String("data:image/jpeg;base64,"+Base64.encode(outputStream.toByteArray()));
        return base64;
    }

    public static byte[] getBytes(String contents) throws WriterException, IOException {
        byte[] bytes = null;
        BufferedImage bufferedImage = encode(contents);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"jpeg",outputStream);
        outputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * 生成条形码
     * @param contents 内容
     * @return
     */
    public static BufferedImage encode(String contents) throws WriterException {
        //配置参数
        Map<EncodeHintType,Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 容错级别 这里选择最高H级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置条码边距 默认为10
        hints.put(EncodeHintType.MARGIN, 0);
        //宽度为条码自动生成规则的宽度
        int width = new Code128Writer().encode(contents).length;
        //高度可由前端控制 不影响编码识别
        int height = 70;
        //条码放大倍数
        int codeMultiples = 1;
        //条码内容宽度
        int codeWidth = width * codeMultiples;
        // 参数分别为 编码内容，编码类型，内容宽度，内容高度，设置参数
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                BarcodeFormat.CODE_128, codeWidth, height, hints);
        return  MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * 解析条形码
     * @param inputStream
     * @return
     */
    public static String decode(InputStream inputStream) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(inputStream);
            if (image == null) {
                throw new RuntimeException("the decode image may be not exists.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
