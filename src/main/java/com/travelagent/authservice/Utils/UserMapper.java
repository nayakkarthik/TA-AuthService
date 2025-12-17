package com.travelagent.authservice.Utils;

import org.springframework.stereotype.Component;

import com.travelagent.authservice.dto.UserInfoDto;
import com.travelagent.authservice.entity.UserInfoEntity;

@Component
public class UserMapper {
    
    public UserInfoEntity toEntity(UserInfoDto userInfoDto)
    {
        var userInfoEntity = new UserInfoEntity();
        userInfoEntity.setEmail(userInfoDto.getEmail());
        userInfoEntity.setUserName(userInfoDto.getUserName());
        userInfoEntity.setPassword(userInfoDto.getPassword());    
        userInfoEntity.setRoles(userInfoDto.getRoles());    
        return userInfoEntity;
    }

    public UserInfoDto toDto(UserInfoEntity entity)
    {
        var userInfoDto = new UserInfoDto(entity.getUserName(),entity.getEmail(),entity.getPassword(),entity.getRoles());
        return userInfoDto;
    }
}
