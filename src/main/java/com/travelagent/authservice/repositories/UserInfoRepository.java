package com.travelagent.authservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelagent.authservice.entity.UserInfoEntity;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity,Long> {

    public Optional<UserInfoEntity> findOptionalByEmail(String email);
    
}
