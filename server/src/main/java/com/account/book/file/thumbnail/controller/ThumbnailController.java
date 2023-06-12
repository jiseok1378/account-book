package com.account.book.file.thumbnail.controller;

import com.account.book.cmmn.global.config.UploadPathConfig;
import com.account.book.cmmn.util.response.SingleResult;
import com.account.book.file.thumbnail.service.ThumbnailService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.account.book.cmmn.util.response.ResponseService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload/thumbnail")
public class ThumbnailController {

    public final UploadPathConfig pathConfig;

    public final ThumbnailService thumbnailService;

    public final ResponseService responseService;

    @PostMapping
    public SingleResult<String> uploadThumbnailTemp(@RequestParam("thumbnail") MultipartFile file) throws IOException {
        return responseService.getSingleResult( thumbnailService.saveTempFile(file) );
    }


}
