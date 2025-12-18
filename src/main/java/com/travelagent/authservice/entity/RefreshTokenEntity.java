package com.travelagent.authservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value ="RefreshToken",timeToLive = 86400)

public class RefreshTokenEntity { 
   
    @Indexed
    private String emailId;

    @Id 
    private String token;
}
 