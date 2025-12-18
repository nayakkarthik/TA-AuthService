package com.travelagent.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
private String token;
private String expiresIn;
private String refreshToken;    
}
