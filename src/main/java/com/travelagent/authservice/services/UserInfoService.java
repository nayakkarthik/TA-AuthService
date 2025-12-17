package com.travelagent.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelagent.authservice.dto.UserInfoDto;
import com.travelagent.authservice.repositories.UserInfoRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserInfoService {

    @Autowired
    private PasswordEncoder PasswordEncoder;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private com.travelagent.authservice.Utils.UserMapper userMapper;

    public void CreateUser(UserInfoDto user) {
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        var userEntity = userMapper.toEntity(user);
         userInfoRepository.save(userEntity);
     }

    public void UpdateUser(UserInfoDto userInfoDto) {

    }
}
