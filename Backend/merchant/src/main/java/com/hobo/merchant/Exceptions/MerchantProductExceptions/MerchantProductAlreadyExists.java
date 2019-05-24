package com.hobo.merchant.Exceptions.MerchantProductExceptions;

import org.json.simple.JSONObject;

public class MerchantProductAlreadyExists extends Exception{
    public MerchantProductAlreadyExists(JSONObject error) {
        super(String.valueOf(error));
    }
}
