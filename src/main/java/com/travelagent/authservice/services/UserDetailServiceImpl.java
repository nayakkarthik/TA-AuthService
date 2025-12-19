package com.travelagent.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelagent.authservice.entity.UserInfoEntity;
import com.travelagent.authservice.repositories.UserInfoRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserInfoEntity userInfoEntity = userInfoRepository.findOptionalByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return User.builder()
                .username(email)
                .password(userInfoEntity.getPassword())
                .roles(userInfoEntity.getRoles())
                .build();
    }

    public boolean ValidateCred(String emailId, String password) {
        return userInfoRepository.findOptionalByEmail(emailId)
                .filter(user -> passwordEncoder.matches(password, user.getPassword())).isPresent();

    }
}
