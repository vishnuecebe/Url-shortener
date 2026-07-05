package com.url.shortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.url.shortener.Repository.UserRepository;
import com.url.shortener.dtos.LoginRequest;
import com.url.shortener.models.User;
import com.url.shortener.security.jwt.JWtUtils;
import com.url.shortener.security.jwt.JwtAuthenticationResponse;
import com.url.shortener.security.jwt.JwtFilter;

import lombok.AllArgsConstructor;

@Service
// @AllArgsConstructor
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWtUtils jWtUtils;
    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetailsImpl =(UserDetailsImpl) authentication.getPrincipal();
        String jwt = jWtUtils.generateToken(userDetailsImpl);
        return new JwtAuthenticationResponse(jwt);
    }
    
    public User findByUserName(String name){
        return userRepository.findByUsername(name).orElseThrow(()-> new UsernameNotFoundException("User not found with username: "+name));
    }
   
}
