package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String username;
    private String type = "Bearer";

    public LoginResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }
}