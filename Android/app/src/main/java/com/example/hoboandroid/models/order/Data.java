package com.example.hoboandroid.models.order;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("productId")
    private int productId;

    @SerializedName("orderId")
    private int orderId;

    @SerializedName("merchantId")
    private int merchantId;

    @SerializedName("productPrice")
    private int productPrice;

    @SerializedName("indexx")
    private int indexx;

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setProductId(int productId){
        this.productId = productId;
    }

    public int getProductId(){
        return productId;
    }

    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    public int getOrderId(){
        return orderId;
    }

    public void setMerchantId(int merchantId){
        this.merchantId = merchantId;
    }

    public int getMerchantId(){
        return merchantId;
    }

    public void setProductPrice(int productPrice){
        this.productPrice = productPrice;
    }

    public int getProductPrice(){
        return productPrice;
    }

    public void setIndexx(int indexx){
        this.indexx = indexx;
    }

    public int getIndexx(){
        return indexx;
    }

    @Override
    public String toString(){
        return
                "Data{" +
                        "quantity = '" + quantity + '\'' +
                        ",productId = '" + productId + '\'' +
                        ",orderId = '" + orderId + '\'' +
                        ",merchantId = '" + merchantId + '\'' +
                        ",productPrice = '" + productPrice + '\'' +
                        ",indexx = '" + indexx + '\'' +
                        "}";
    }
}