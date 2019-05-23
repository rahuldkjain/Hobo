package com.example.hoboandroid.models;

import android.widget.RatingBar;

import com.google.gson.annotations.SerializedName;

public class OrderedProduct {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("product_id")
    private String productId;

    @SerializedName("merchant_id")
    private String merchantId;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("product_price")
    private String productPrice;

    private RatingBar productRatingBar;

    private RatingBar merchantRatingBar;

    public RatingBar getProductRatingBar() {
        return productRatingBar;
    }

    public void setProductRatingBar(RatingBar productRatingBar) {
        this.productRatingBar = productRatingBar;
    }

    public RatingBar getMerchantRatingBar() {
        return merchantRatingBar;
    }

    public void setMerchantRatingBar(RatingBar merchantRatingBar) {
        this.merchantRatingBar = merchantRatingBar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }



    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }




}
