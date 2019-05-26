package com.example.hoboandroid.models.order;

import com.google.gson.annotations.SerializedName;

public class OrderData {

    @SerializedName("pincode")
    private int pincode;

    @SerializedName("userEmailId")
    private String userEmailId;

    @SerializedName("orderId")
    private int orderId;

    @SerializedName("address2")
    private String address2;

    @SerializedName("city")
    private String city;

    @SerializedName("address1")
    private String address1;

    @SerializedName("orderPrice")
    private int orderPrice;

    @SerializedName("deliveryDate")
    private long deliveryDate;

    @SerializedName("userId")
    private int userId;

    @SerializedName("orderDate")
    private long orderDate;

    public void setPincode(int pincode){
        this.pincode = pincode;
    }

    public int getPincode(){
        return pincode;
    }

    public void setUserEmailId(String userEmailId){
        this.userEmailId = userEmailId;
    }

    public String getUserEmailId(){
        return userEmailId;
    }

    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    public int getOrderId(){
        return orderId;
    }

    public void setAddress2(String address2){
        this.address2 = address2;
    }

    public String getAddress2(){
        return address2;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }

    public void setAddress1(String address1){
        this.address1 = address1;
    }

    public String getAddress1(){
        return address1;
    }

    public void setOrderPrice(int orderPrice){
        this.orderPrice = orderPrice;
    }

    public int getOrderPrice(){
        return orderPrice;
    }

    public void setDeliveryDate(long deliveryDate){
        this.deliveryDate = deliveryDate;
    }

    public long getDeliveryDate(){
        return deliveryDate;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    public void setOrderDate(long orderDate){
        this.orderDate = orderDate;
    }

    public long getOrderDate(){
        return orderDate;
    }

    @Override
    public String toString(){
        return
                "Data{" +
                        "pincode = '" + pincode + '\'' +
                        ",userEmailId = '" + userEmailId + '\'' +
                        ",orderId = '" + orderId + '\'' +
                        ",address2 = '" + address2 + '\'' +
                        ",city = '" + city + '\'' +
                        ",address1 = '" + address1 + '\'' +
                        ",orderPrice = '" + orderPrice + '\'' +
                        ",deliveryDate = '" + deliveryDate + '\'' +
                        ",userId = '" + userId + '\'' +
                        ",orderDate = '" + orderDate + '\'' +
                        "}";
    }
}