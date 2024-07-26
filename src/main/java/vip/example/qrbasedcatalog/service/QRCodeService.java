package vip.example.qrbasedcatalog.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QRCodeService {

    public byte[] generateQRCode(String url) throws WriterException, IOException {
        int qrCodeSize = 300;
        int padding = 20;
        int textPadding = 10;
        int maxWidth = 600; // Maximum width of the image

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                url,
                BarcodeFormat.QR_CODE,
                qrCodeSize,
                qrCodeSize,
                hints
        );

        BufferedImage qrImage = new BufferedImage(qrCodeSize, qrCodeSize, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < qrCodeSize; x++) {
            for (int y = 0; y < qrCodeSize; y++) {
                qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        // Calculate text dimensions and wrap text
        Font font = new Font("Arial", Font.PLAIN, 12);
        FontMetrics fontMetrics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(font);
        List<String> lines = wrapText(url, fontMetrics, maxWidth - (2 * padding));
        int textHeight = fontMetrics.getHeight() * lines.size();

        // Calculate image dimensions
        int imageWidth = Math.max(qrCodeSize, maxWidth);
        int imageHeight = qrCodeSize + textHeight + (2 * padding) + textPadding;

        BufferedImage combinedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = combinedImage.createGraphics();

        // Set background to white
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, imageWidth, imageHeight);

        // Draw QR code
        int qrCodeX = (imageWidth - qrCodeSize) / 2;
        g2.drawImage(qrImage, qrCodeX, padding, null);

        // Draw URL text
        g2.setColor(Color.BLACK);
        g2.setFont(font);
        int textY = qrCodeSize + padding + textPadding + fontMetrics.getAscent();
        for (String line : lines) {
            int textX = (imageWidth - fontMetrics.stringWidth(line)) / 2;
            g2.drawString(line, textX, textY);
            textY += fontMetrics.getHeight();
        }

        g2.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(combinedImage, "png", baos);
        return baos.toByteArray();
    }

    private List<String> wrapText(String text, FontMetrics fontMetrics, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split("\\s+");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (fontMetrics.stringWidth(line + " " + word) <= maxWidth) {
                if (line.length() > 0) line.append(" ");
                line.append(word);
            } else {
                if (line.length() > 0) lines.add(line.toString());
                line = new StringBuilder(word);
            }
        }

        if (line.length() > 0) lines.add(line.toString());

        return lines;
    }
}