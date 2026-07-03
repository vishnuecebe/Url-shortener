package com.url.shortener.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.shortener.models.UrlMapping;
import com.url.shortener.models.User;

import java.util.List;


@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long>  {
    UrlMapping findByShortUrl(String shortUrl);
    List<UrlMapping> findByUser(User user);
}
