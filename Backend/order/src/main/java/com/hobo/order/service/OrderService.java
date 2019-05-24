package com.hobo.order.service;


import com.hobo.order.Exceptions.orderExceptions.OrderAlreadyExists;
import com.hobo.order.Exceptions.orderExceptions.OrderNotFound;
import com.hobo.order.entity.OrderEntity;
import com.hobo.order.model.OrderDTO;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO) throws OrderAlreadyExists;
    OrderDTO readOrder(int orderItemId) throws OrderNotFound;
    OrderDTO deleteOrder(int orderItemId) throws OrderNotFound;
    OrderDTO updateOrder(OrderDTO orderDTO) throws OrderNotFound;
    OrderDTO updateDeliveryDate(Integer orderId, Date deliveryDate) throws OrderNotFound;

    List<OrderEntity> getAllOrder(Integer userId);
}
