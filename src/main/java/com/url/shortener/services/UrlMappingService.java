package com.url.shortener.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.shortener.Repository.UrlMappingRepository;
import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.models.UrlMapping;
import com.url.shortener.models.User;
@Service
public class UrlMappingService {
    @Autowired
    private UrlMappingRepository urlMappingRepository;
    public UrlMappingDTO createShortUrl(String originalUrl, User user) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setCreatedDate(LocalDateTime.now());
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        UrlMapping saveUrlMapping = urlMappingRepository.save(urlMapping);
        return converttoDto(saveUrlMapping);

    }
    private UrlMappingDTO converttoDto(UrlMapping urlMapping){
        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setUsername(urlMappingDTO.getUsername());
        urlMappingDTO.setClickCount(urlMapping.getClickCount());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
        return urlMappingDTO;
    }
    private String generateShortUrl(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder shortUrl= new StringBuilder(8);
        for(int i=0;i<8;i++){
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
    
}
