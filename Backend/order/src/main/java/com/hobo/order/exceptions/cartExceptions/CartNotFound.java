package com.hobo.order.exceptions.cartExceptions;


import org.json.simple.JSONObject;

public class CartNotFound extends Exception {
    public CartNotFound(JSONObject error){
        super(String.valueOf(error));
    }
}