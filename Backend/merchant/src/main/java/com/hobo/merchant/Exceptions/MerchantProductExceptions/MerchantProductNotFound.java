package com.hobo.merchant.Exceptions.MerchantProductExceptions;

import org.json.simple.JSONObject;

public class MerchantProductNotFound extends Exception{
    public MerchantProductNotFound(JSONObject error){
        super(String.valueOf(error));
    }
}
