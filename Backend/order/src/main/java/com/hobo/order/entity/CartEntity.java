package com.hobo.order.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;
    private String userEmail;
    private int productId;
    private int merchantId;
    private int quantity;
    private float productPrice;


    //getters and setters for cart defined variables

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


    //Overriding toString


    @Override
    public String toString() {
        return "CartEntity{" +
                "cartItemId=" + cartItemId +
                ", userId=" + userEmail +
                ", productId=" + productId +
                ", merchantId=" + merchantId +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                '}';
    }
}
