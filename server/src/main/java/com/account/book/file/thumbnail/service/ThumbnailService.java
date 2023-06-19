package com.account.book.file.thumbnail.service;

import com.account.book.cmmn.global.config.UploadPathConfig;
import com.account.book.cmmn.util.image.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class ThumbnailService {

    private final UploadPathConfig pathConfig;

    private boolean checkUploadPath( Path uploadPath ){
        File uploadPathF = uploadPath.toFile();
        System.out.println( "UPLOAD PATH: " + uploadPathF.getAbsolutePath() );
        if( !uploadPathF.exists() ) return uploadPathF.mkdir();
        else return true;
    }



    public String saveTempFile(MultipartFile multipartFile) throws Exception {

        Path saveTarget = pathConfig.getThumbnailPath();

        if( !checkUploadPath( saveTarget ) ) {
            System.err.println("ERROR: SAVE UPLOAD DIR");
            return "ERROR";
        }

        if( !multipartFile.isEmpty() ) {
            String ext =  FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String tmpNm = "TEMP-" + System.currentTimeMillis() + "." + ext;

            Path savedPath = saveTarget.resolve( tmpNm ).normalize();

            return ImageUtil.resizeAndSave(multipartFile, 120, 120, ext, savedPath);
        }
        System.err.println("ERROR: MULTIPART FILE IS EMPTY");
        return "ERROR";
    }

    public UrlResource getTempFile(String fileNm) throws MalformedURLException {
        Path filePath = Path.of(pathConfig.getThumbnailPath().toString(), fileNm);
        UrlResource resource = new UrlResource(filePath.toUri());
        return resource;
    }
}
