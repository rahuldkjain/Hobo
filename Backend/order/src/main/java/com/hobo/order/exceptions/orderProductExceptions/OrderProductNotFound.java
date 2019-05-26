package com.hobo.order.exceptions.orderProductExceptions;

public class OrderProductNotFound extends Exception {
    public OrderProductNotFound(String error){
        super(error);
    }
}