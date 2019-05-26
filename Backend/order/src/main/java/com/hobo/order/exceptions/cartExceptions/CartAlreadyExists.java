package com.hobo.order.exceptions.cartExceptions;

public class CartAlreadyExists extends Exception{
    public CartAlreadyExists(String error) {
        super(error);
    }
}