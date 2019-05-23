package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

public class OrderedProduct {


    @SerializedName("order_date")
    private String orderDate;

    @SerializedName("“user_id”")
    private String userId;

    @SerializedName("order_price")
    private String orderPrice;

    @SerializedName("address_1")
    private String address1;

    @SerializedName("address_2")
    private String address_2;

    @SerializedName("city")
    private String city;

    @SerializedName("pincode")
    private String pincode;



}
