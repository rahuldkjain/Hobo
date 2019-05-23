package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Order {
    @SerializedName("order_id")
    private String orderId;

    @SerializedName("order_date")
    private String orderDate;


    @SerializedName("“user_id”")
    private String userId;

    @SerializedName("delivery_date")
    private String deliveryDate;

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

    public String getDeliveryAddress(){
        return getAddress1()+" "+getAddress_2()+" "+getCity()+"-"+getPincode();
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
