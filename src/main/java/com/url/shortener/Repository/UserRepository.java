package com.url.shortener.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url.shortener.models.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long>{

    Optional <User> findByUsername(String username);
}
