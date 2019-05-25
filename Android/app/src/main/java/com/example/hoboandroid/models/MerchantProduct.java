package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

public class MerchantProduct {
    @SerializedName("productId")
    private int productId;

    @SerializedName("merchantId")
    private int merchantId;

    @SerializedName("price")
    private int price;

    @SerializedName("merchantScore")
    private float merchantScore;

    @SerializedName("productsSold")
    private int productsSold;

    @SerializedName("stock")
    private int stock;

    @SerializedName("productRating")
    private float productRating;

    @SerializedName("indexx")
    private int indexx;

    public void setProductId(int productId){
        this.productId = productId;
    }

    public int getProductId(){
        return productId;
    }

    public void setMerchantId(int merchantId){
        this.merchantId = merchantId;
    }

    public int getMerchantId(){
        return merchantId;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public void setMerchantScore(int merchantScore){
        this.merchantScore = merchantScore;
    }

    public float getMerchantScore(){
        return merchantScore;
    }

    public void setProductsSold(int productsSold){
        this.productsSold = productsSold;
    }

    public int getProductsSold(){
        return productsSold;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public int getStock(){
        return stock;
    }

    public void setProductRating(float productRating){
        this.productRating = productRating;
    }

    public float getProductRating(){
        return productRating;
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
                "Response{" +
                        "productId = '" + productId + '\'' +
                        ",merchantId = '" + merchantId + '\'' +
                        ",price = '" + price + '\'' +
                        ",merchantScore = '" + merchantScore + '\'' +
                        ",productsSold = '" + productsSold + '\'' +
                        ",stock = '" + stock + '\'' +
                        ",productRating = '" + productRating + '\'' +
                        ",indexx = '" + indexx + '\'' +
                        "}";
    }
}
