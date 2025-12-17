package com.travelagent.authservice.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelagent.authservice.Utils.JwtUtil;
import com.travelagent.authservice.dto.UserInfoDto;
import com.travelagent.authservice.services.UserDetailServiceImpl; 

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailServiceImpl userDetailServiceImp;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UserInfoDto userInfoDto) {

       var isCredentialValid =  userDetailServiceImp.ValidateCred(userInfoDto.getEmail(), userInfoDto.getPassword());
      if(!isCredentialValid)
      {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
      }
      var user = userDetailServiceImp.loadUserByUsername(userInfoDto.getEmail());
      String jwt = jwtUtil.getToken(user);
      return ResponseEntity.ok(Map.of(
            "token",jwt,
            "expires",(5*60),
            "refreshToken","to be coded"
      ));

    }

    @PostMapping("/logout")
    public void Logout() {
//add jtid to redis with ttl. ttl should be expiry time of the token
    }

    @PostMapping("/validate")
    public void validate()
    {

    }

}
