package com.url.shortener.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.url.shortener.Repository.UserRepository;
import com.url.shortener.models.User;

import jakarta.transaction.Transactional;

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserRepository userRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("User not found "));
        return UserDetailsImpl.build(user);
    }
    
}
