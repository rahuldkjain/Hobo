package com.hobo.order.Exceptions.orderProductExceptions;


import org.json.simple.JSONObject;

public class OrderProductNotFound extends Exception {
    public OrderProductNotFound(JSONObject error){
        super(String.valueOf(error));
    }
}