package com.hobo.order.repository;

import com.hobo.order.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Integer> {
    List<CartEntity> findByUserEmail(String emailId);

    boolean existsByUserEmailAndProductIdAndMerchantId(String userEmail, int productId, int merchantId);
}
