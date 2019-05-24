package com.hobo.order.Exceptions.orderExceptions;


import org.json.simple.JSONObject;

public class OrderNotFound extends Exception {
    public OrderNotFound(JSONObject error){
        super(String.valueOf(error));
    }
}