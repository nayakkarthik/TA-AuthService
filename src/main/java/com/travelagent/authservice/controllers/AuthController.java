package com.travelagent.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelagent.authservice.dto.LoginResponse;
import com.travelagent.authservice.dto.UserInfoDto;
import com.travelagent.authservice.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> Login(@RequestBody UserInfoDto userInfoDto) {

    LoginResponse loginResponse = authService.ValidateAndGenerateToken(userInfoDto);

    if (loginResponse == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
    }

    return ResponseEntity.ok(loginResponse);

  }

  @GetMapping("/{refreshToken}")
  public ResponseEntity<?> RefreshAuth(@PathVariable String refreshToken) {
    var loginResp = authService.RotateAndRefresh(refreshToken);
    if (loginResp == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token is expired or does not exist.");
    }

    return ResponseEntity.ok(loginResp);
  }

  @PostMapping("/logout")
  public void Logout() {
    /*
     * 1. jwt filter should validate the token.
     * 2. refreshToken should be deleted
     */

    // add jtid to redis with ttl. ttl should be expiry time of the token
  }

  @PostMapping("/validate")
  public void validate() {
//to be designed.
  }

}
