package com.hobo.order.repository;

import com.hobo.order.entity.CartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Integer> {
    ArrayList<CartEntity> findByUserEmail(String emailId);

    //@Query("delete from Cart where userEmail=:email")
    //void delete(@Param("email") String email);

    CartEntity findByUserEmailAndProductIdAndMerchantId(String userEmail, int productId, int merchantId);
}
