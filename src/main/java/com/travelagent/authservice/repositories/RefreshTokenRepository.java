package com.travelagent.authservice.repositories;
 

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.travelagent.authservice.entity.RefreshTokenEntity;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity,String> {
     List<RefreshTokenEntity> findAllByEmailId(String emailId);
     
}
