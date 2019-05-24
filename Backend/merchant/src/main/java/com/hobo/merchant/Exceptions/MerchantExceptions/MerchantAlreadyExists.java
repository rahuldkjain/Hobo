package com.hobo.merchant.Exceptions.MerchantExceptions;

import org.json.simple.JSONObject;

public class MerchantAlreadyExists extends Exception{
    public MerchantAlreadyExists(JSONObject error) {
        super(String.valueOf(error));
    }
}