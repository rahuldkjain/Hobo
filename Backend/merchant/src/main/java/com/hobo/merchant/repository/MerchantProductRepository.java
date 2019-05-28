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

    @Query("select mp.indexx, m.merchantId, mp.productId, mp.stock, mp.price, mp.productRating, mp.productsSold, mp.merchantScore, m.merchantName from MerchantProduct mp, Merchant m where mp.merchantId=m.merchantId and mp.productId=:productId order by mp.merchantScore desc")
    List<Object> test (@Param("productId") Integer productId);


    List<MerchantProduct> findByProductIdOrderByMerchantScoreDesc(Integer productId);
}
