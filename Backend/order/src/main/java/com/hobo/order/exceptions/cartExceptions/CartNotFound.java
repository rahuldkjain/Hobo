package com.hobo.order.exceptions.cartExceptions;

public class CartNotFound extends Exception {
    public CartNotFound(String error){
        super(error);
    }
}