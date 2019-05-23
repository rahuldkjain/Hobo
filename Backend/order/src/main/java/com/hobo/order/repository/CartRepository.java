package com.hobo.order.repository;

import com.hobo.order.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Integer> {
}
