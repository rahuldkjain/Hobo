package com.hobo.order.exceptions.orderExceptions;

import org.json.simple.JSONObject;

public class OrderAlreadyExists extends Exception{
    public OrderAlreadyExists(JSONObject error) {
        super(String.valueOf(error));
    }
}