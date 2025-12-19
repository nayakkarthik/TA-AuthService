package com.travelagent.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "userinfo")
public class UserInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
 
    @Column(nullable = false,unique = true) 
    private String email;

     @Column(nullable = false)
     private String password;

    @Column(nullable = false)
     private String[] roles; 
}
