package com.account.book.file.thumbnail.controller;

import com.account.book.file.thumbnail.service.ThumbnailService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/upload/thumbnail")
@RequiredArgsConstructor
public class ThumbnailLoadController {

    private final ThumbnailService thumbnailService;

    @GetMapping(value = "/{path}", produces = MediaType.IMAGE_PNG_VALUE)
    public UrlResource getThumbnailTemp(@PathVariable("path") String path) throws MalformedURLException {
        return  thumbnailService.getTempFile(path) ;
    }
}
