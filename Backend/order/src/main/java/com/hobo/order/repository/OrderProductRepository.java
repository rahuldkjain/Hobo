package com.hobo.order.repository;

import com.hobo.order.entity.OrderProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProductEntity,Integer> {
    List<OrderProductEntity> findByOrderId(Integer orderId);
}
