package com.hobo.order.service;


import com.hobo.order.model.OrderDTO;

public interface OrderService {


    //Cart API create
    OrderDTO createOrder(OrderDTO orderDTO);

    //read from cart by ID
    OrderDTO readOrder(int orderItemId);

    //delete Cart by ID
    OrderDTO deleteOrder(int orderItemId);

    //Update/Add to cart
    OrderDTO updateOrder(OrderDTO orderDTO);
}
