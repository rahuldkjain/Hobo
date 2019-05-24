package com.hobo.order.model;

public class CartDTO {

    //Cart info variables declared -->
    private Integer cartItemId;
    private int userId;
    private int productId;
    private int merchantId;
    private int quantity;
    private float productPrice;



    //getters and setters for Cart data variables -->


    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartItemId=" + cartItemId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", merchantId=" + merchantId +
                ", quantity=" + quantity +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }
}
