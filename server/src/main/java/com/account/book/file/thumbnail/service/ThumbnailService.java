package com.account.book.file.thumbnail.service;

import com.account.book.cmmn.global.config.UploadPathConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class ThumbnailService {

    private final UploadPathConfig pathConfig;

    private boolean checkUploadPath( Path uploadPath ){
        File uploadPathF = uploadPath.toFile();
        if( !uploadPathF.exists() ) return uploadPathF.mkdir();
        else return true;
    }

    private BufferedImage simpleResizeImage(InputStream originalImage, int width, int height) throws Exception {
        BufferedImage inputImg = ImageIO.read(originalImage);
        BufferedImage outputImage =
                new BufferedImage(width, height, inputImg.getType());

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(inputImg, 0, 0, width, height, null);
        graphics2D.dispose();

        return outputImage;
    }

    public String saveTempFile(MultipartFile multipartFile) throws Exception {

        Path saveTarget = pathConfig.getThumbnailPath();

        if( !checkUploadPath( saveTarget ) ) return "ERROR";

        if( !multipartFile.isEmpty() ) {
            String tmpNm = "TEMP-" + System.currentTimeMillis() + "." +  FilenameUtils.getExtension(multipartFile.getOriginalFilename());

            Path savedPath = saveTarget.resolve( tmpNm ).normalize();
            InputStream imageIs = multipartFile.getInputStream();

            ImageIO.write( simpleResizeImage(imageIs, 120, 120), "png", savedPath.toFile() );
            multipartFile.transferTo( savedPath );

            imageIs.close();

            return savedPath.toString();
        }
        return "ERROR";
    }

    public UrlResource getTempFile(String fileNm) throws MalformedURLException {
        Path filePath = Path.of(pathConfig.getThumbnailPath().toString(), fileNm);
        UrlResource resource = new UrlResource(filePath.toUri());
        return resource;
    }
}
