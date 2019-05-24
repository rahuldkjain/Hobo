package com.hobo.user.repository;

import com.hobo.user.entity.MerchantUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface MerchantUserRepository extends CrudRepository<MerchantUserEntity, Integer> {

    MerchantUserEntity findByEmailId(String email);

    boolean existsByEmailId(String emailId);
}
