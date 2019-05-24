package com.hobo.merchant.Exceptions.MerchantExceptions;


import org.json.simple.JSONObject;

public class MerchantNotFound extends Exception {
    public MerchantNotFound(JSONObject error){
        super(String.valueOf(error));
    }
}