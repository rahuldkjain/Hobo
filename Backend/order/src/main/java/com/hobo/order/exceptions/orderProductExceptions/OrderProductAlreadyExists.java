package com.hobo.order.exceptions.orderProductExceptions;

public class OrderProductAlreadyExists extends Exception{
    public OrderProductAlreadyExists(String error) {
        super(error);
    }
}