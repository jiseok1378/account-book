package com.account.book.cmmn.util.image;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class ImageUtil {

    private static BufferedImage simpleResizeImage(InputStream originalImage, int width, int height) throws Exception {
        BufferedImage inputImg = ImageIO.read(originalImage);
        BufferedImage outputImage =
                new BufferedImage(width, height, inputImg.getType());

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(inputImg, 0, 0, width, height, null);
        graphics2D.dispose();

        return outputImage;
    }

    public static String resizeAndSave(MultipartFile multipartFile, int width, int height, String ext, Path savedPath) throws Exception {
        InputStream imageIs = multipartFile.getInputStream();

        ImageIO.write( simpleResizeImage(imageIs, width, height), "png", savedPath.toFile() );

        imageIs.close();
        return savedPath.toString();
    }
}
