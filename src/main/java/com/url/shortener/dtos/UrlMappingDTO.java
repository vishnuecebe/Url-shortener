package com.url.shortener.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UrlMappingDTO {
    private Long Id;
    private String originalUrl;
    private String ShortUrl;
    private int clickCount;
    private LocalDateTime createdDate;
    private String username;
    
}
