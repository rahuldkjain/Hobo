package com.hobo.order.Exceptions.cartExceptions;

import org.json.simple.JSONObject;

public class CartAlreadyExists extends Exception{
    public CartAlreadyExists(JSONObject error) {
        super(String.valueOf(error));
    }
}