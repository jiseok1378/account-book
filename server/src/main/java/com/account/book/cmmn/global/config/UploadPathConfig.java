package com.account.book.cmmn.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@ConfigurationProperties("upload")
@Getter
@Setter
public class UploadPathConfig {
    private String path;

    public Path getThumbnailPath(){
        return Path.of(path, "thumbnail");
    }

}
