package com.hobo.merchant.repository;

import com.hobo.merchant.entity.JoinedTable;
import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.entity.MerchantProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MerchantRepositoryImpl extends CrudRepository<Merchant,Integer> {
    @Query(value ="select m.merchant_id,m.merchant_name,m.merchant_rating,m.description,m.address,m.email,m.phone_number,mp.indexx,mp.product_id,mp.stock,mp.price,mp.product_rating,mp.products_sold from merchant m inner join merchantproduct mp on m.merchant_id = mp.merchant_id where merchant_score = (select MAX(merchant_score) from merchant);",nativeQuery = true)
    List<JoinedTable> findTopMerchant();
}
