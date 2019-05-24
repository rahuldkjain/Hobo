package com.hobo.order.Exceptions.orderExceptions;

import org.json.simple.JSONObject;

public class OrderAlreadyExists extends Exception{
    public OrderAlreadyExists(JSONObject error) {
        super(String.valueOf(error));
    }
}