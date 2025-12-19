package com.travelagent.authservice.services;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlacklistService {

    private final String blacklistKey = "blacklist:";

    private final Function<String,String> getKey = jti -> blacklistKey+jti;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void Blacklist(String jti, long ttl) {
        if (ttl > 0) {
             
            redisTemplate.opsForValue().setIfAbsent(getKey.apply(jti), "1", ttl,TimeUnit.SECONDS);
        }
    }

    public boolean isBlackListed(String jti) {
        String val = (String) redisTemplate.opsForValue().get(getKey.apply(jti));
        return val != null;
    }
}
