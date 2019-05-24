package com.hobo.merchant.repository;

import com.hobo.merchant.entity.MerchantProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MerchantProductRepositoryImpl extends CrudRepository<MerchantProduct, Integer> {
    List<MerchantProduct> findByMerchantId(Integer merchantId);

}
