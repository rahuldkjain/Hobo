package com.hobo.order.exceptions.orderProductExceptions;

import org.json.simple.JSONObject;

public class OrderProductAlreadyExists extends Exception{
    public OrderProductAlreadyExists(JSONObject error) {
        super(String.valueOf(error));
    }
}