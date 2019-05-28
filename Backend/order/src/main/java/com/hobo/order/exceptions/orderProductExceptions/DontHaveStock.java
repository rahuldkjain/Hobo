package com.hobo.order.exceptions.orderProductExceptions;

public class DontHaveStock extends Exception{
    public DontHaveStock(String error) {
        super(error);
    }
}
