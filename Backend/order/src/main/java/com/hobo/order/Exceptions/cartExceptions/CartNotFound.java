package com.hobo.order.Exceptions.cartExceptions;


import org.json.simple.JSONObject;

public class CartNotFound extends Exception {
    public CartNotFound(JSONObject error){
        super(String.valueOf(error));
    }
}