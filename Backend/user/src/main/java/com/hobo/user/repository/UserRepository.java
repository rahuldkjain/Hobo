package com.hobo.user.repository;

import com.hobo.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByEmailId(String email);

    boolean existsByEmailId(String emailId);
}
