package com.hobo.merchant.repository;

import com.hobo.merchant.entity.Merchant;
import org.springframework.data.repository.CrudRepository;


public interface MerchantRepository extends CrudRepository<Merchant,Integer> {
}
