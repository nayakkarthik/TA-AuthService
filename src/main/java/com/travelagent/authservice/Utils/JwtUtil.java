package com.travelagent.authservice.Utils;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Value("${spring.application.tokenExpiryTime}")
    private long tokenExpiryTime;

    private static final String SECRET_KEY = "JWTFQ1Ux2P4Ke1D35xKhlxiA/NdA2yqRkXQW7ZGPy3N+sU=";

    public String getToken(UserDetails user) {
        
        return Jwts.builder()
                .subject(user.getUsername())
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiryTime)) // expires in 60 seconds.
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getClaim(String token)
    {
        return Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
 
    }
}
