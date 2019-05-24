package com.hobo.order.service;

import com.hobo.order.Exceptions.orderProductExceptions.OrderProductAlreadyExists;
import com.hobo.order.Exceptions.orderProductExceptions.OrderProductNotFound;
import com.hobo.order.entity.OrderProductEntity;
import com.hobo.order.model.OrderDTO;
import com.hobo.order.model.OrderProductDTO;

import java.util.List;

public interface OrderProductService {
    OrderProductDTO createOrderProduct(OrderProductDTO orderProductDTO) throws OrderProductAlreadyExists;
    List<OrderProductEntity> readByOrderId(Integer orderId);

}
