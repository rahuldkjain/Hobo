package com.example.hoboandroid.models;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Order{

    @SerializedName("pincode")
    private Integer pincode;

    @SerializedName("orderId")
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @SerializedName("userEmailId")
    private String userEmailId;

    @SerializedName("address2")
    private String address2;

    @SerializedName("city")
    private String city;

    @SerializedName("address1")
    private String address1;


    @SerializedName("orderPrice")
    private Integer orderPrice;

    @SerializedName("deliveryDate")
    private Date deliveryDate;


    @SerializedName("orderDate")
    private Date orderDate;

    @SerializedName("userId")
    private Integer userId;

    public void setPincode(Integer pincode){
        this.pincode = pincode;
    }
    public Integer getPincode(){
        return pincode;
    }
    public void setUserEmailId(String userEmailId){
        this.userEmailId = userEmailId;
    }
    public String getUserEmailId(){
        return userEmailId;
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
    public void setOrderPrice(Integer orderPrice){
        this.orderPrice = orderPrice;
    }
    public Integer getOrderPrice(){
        return orderPrice;
    }
    public void setDeliveryDate(Date deliveryDate){
        this.deliveryDate = deliveryDate;
    }
    public Date getDeliveryDate(){
        return deliveryDate;
    }
    public void setOrderDate(Date orderDate){
        this.orderDate = orderDate;
    }
    public Date getOrderDate(){
        return orderDate;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }
    public Integer getUserId(){
        return userId;
    }
    @Override
    public String toString(){
        return
                "Order{" +
                        "pincode = '" + pincode + '\'' +
                        ",userEmailId = '" + userEmailId + '\'' +
                        ",address2 = '" + address2 + '\'' +
                        ",city = '" + city + '\'' +
                        ",address1 = '" + address1 + '\'' +
                        ",orderPrice = '" + orderPrice + '\'' +
                        ",deliveryDate = '" + deliveryDate + '\'' +
                        ",orderDate = '" + orderDate + '\'' +
                        ",userId = '" + userId + '\'' +
                        "}";
    }
}
