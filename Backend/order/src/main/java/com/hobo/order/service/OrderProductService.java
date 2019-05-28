package com.hobo.order.service;

import com.hobo.order.exceptions.orderProductExceptions.DontHaveStock;
import com.hobo.order.exceptions.orderProductExceptions.OrderProductAlreadyExists;
import com.hobo.order.entity.OrderProductEntity;
import com.hobo.order.model.OrderProductDTO;

import java.util.List;

public interface OrderProductService {
    OrderProductDTO createOrderProduct(OrderProductDTO orderProductDTO) throws OrderProductAlreadyExists, DontHaveStock;
    List<OrderProductEntity> readByOrderId(Integer orderId);

}
