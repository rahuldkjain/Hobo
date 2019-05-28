package com.hobo.order.model;

import java.util.ArrayList;

public class UserCartDTO {

    private int cartItemId;
    private String userEmail;
    private int productId;
    private String productName;
    private ArrayList<String> productImage;
    private int merchantId;
    private int quantity;
    private float productPrice;
    private String merchantName;
    private int stock;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "UserCartDTO{" +
                "cartItemId=" + cartItemId +
                ", userEmail='" + userEmail + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productImage=" + productImage +
                ", merchantId=" + merchantId +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                ", merchantName='" + merchantName + '\'' +
                ", stock=" + stock +
                '}';
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public ArrayList<String> getProductImage() {
        return productImage;
    }

    public void setProductImage(ArrayList<String> productImage) {
        this.productImage = productImage;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

}
