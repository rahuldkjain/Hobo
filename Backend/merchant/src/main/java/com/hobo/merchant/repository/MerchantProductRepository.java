package com.hobo.merchant.repository;

import com.hobo.merchant.entity.MerchantProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MerchantProductRepository extends CrudRepository<MerchantProduct, Integer> {
    List<MerchantProduct> findByMerchantId(Integer merchantId);
    List<MerchantProduct> findByProductId(Integer productId);

    @Query("SELECT COUNT(mp) FROM MerchantProduct mp WHERE mp.merchantId=:id")
    Integer countProductsMerchantSell(@Param("id") Integer id);

}
