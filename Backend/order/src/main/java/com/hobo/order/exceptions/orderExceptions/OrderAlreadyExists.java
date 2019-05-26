package com.hobo.order.exceptions.orderExceptions;

public class OrderAlreadyExists extends Exception{
    public OrderAlreadyExists(String error) {
        super(error);
    }
}