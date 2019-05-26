package com.example.hoboandroid.models.merchantproduct;

import com.google.gson.annotations.SerializedName;

public class MerchantProductResponse {

    @SerializedName("productId")
    private String productId;

    @SerializedName("merchantId")
    private String merchantId;

    @SerializedName("price")
    private String price;

    @SerializedName("merchantScore")
    private String merchantScore;

    @SerializedName("productsSold")
    private String productsSold;

    @SerializedName("stock")
    private String stock;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMerchantScore() {
        return merchantScore;
    }

    public void setMerchantScore(String merchantScore) {
        this.merchantScore = merchantScore;
    }

    public String getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(String productsSold) {
        this.productsSold = productsSold;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public String getIndexx() {
        return indexx;
    }

    public void setIndexx(String indexx) {
        this.indexx = indexx;
    }

    @SerializedName("productRating")
    private String productRating;

    @SerializedName("indexx")
    private String indexx;

}
