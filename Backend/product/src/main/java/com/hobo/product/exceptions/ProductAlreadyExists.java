package com.hobo.product.exceptions;

import org.json.simple.JSONObject;

public class ProductAlreadyExists extends Exception{
    public ProductAlreadyExists(JSONObject error){
        super(String.valueOf(error));
    }
}
