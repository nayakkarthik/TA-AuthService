package com.travelagent.authservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheck {
    
    @GetMapping
    public ResponseEntity<?> healthCheckOK()
    {
        return ResponseEntity.ok().body("hey, i am healthy!");
    }
}
