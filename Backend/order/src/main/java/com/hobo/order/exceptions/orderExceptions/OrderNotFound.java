package com.hobo.order.exceptions.orderExceptions;

public class OrderNotFound extends Exception {
    public OrderNotFound(String error){
        super(error);
    }
}