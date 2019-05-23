package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Cart {
    @SerializedName("merchant_id")
    private String merchantId;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("cart_item_id")
    private String cartItemId;

    @SerializedName("product_id")
    private String productId;

    @SerializedName("product_price")
    private String productPrice;

    @SerializedName("quantity")
    private String quantity;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }



}
