package com.hobo.product.exceptions;

import org.json.simple.JSONObject;

public class ProductNotFound extends Exception{
    public ProductNotFound(JSONObject error){
        super(String.valueOf(error));
    }
}
