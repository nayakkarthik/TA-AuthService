package com.travelagent.authservice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelagent.authservice.entity.RefreshTokenEntity;

@Service
public class RefreshTokenService {

    @Autowired
    private com.travelagent.authservice.repositories.RefreshTokenRepository refreshTokenRepository;

    public String getRefreshToken(String emailId) { 
        deleteAllByEmail(emailId);
        var token = new RefreshTokenEntity(emailId,UUID.randomUUID().toString());
        refreshTokenRepository.save(token);
        return token.getToken();
    }

    public String getEmailId(String refreshToken) {
    
        var token = refreshTokenRepository.findById(refreshToken);
        if(token.isPresent())
        {
            return token.get().getEmailId();
        }
        else
        {
            return null;
        }
    }

      public void deleteAllByEmail(String email) {
        List<RefreshTokenEntity> list = refreshTokenRepository.findAllByEmailId(email);
        refreshTokenRepository.deleteAll(list);
    } 
}
