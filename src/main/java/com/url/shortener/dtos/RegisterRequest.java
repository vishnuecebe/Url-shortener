package com.url.shortener.dtos;

import java.util.Set;

import lombok.Data;
@Data
public class RegisterRequest {
    private String usrname;
    private String email;
    private Set<String> role;
    private String password;
}
