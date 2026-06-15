package com.example.demo.security;

import org.springframework.stereotype.Component;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtils {
    @Value("${app.jwt.secret}")
    private String jwtSecretString;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecretString.getBytes());
    }

    public String generateJwtToken(Authentication authentication) {
        return Jwts.builder().setSubject(authentication.getName()).signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Key getSigningKey() {
        byte[] keyBytes = jwtSecretString.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}