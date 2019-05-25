package com.example.hoboandroid.models.product;

import com.example.hoboandroid.models.Attributes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListItem {

    private String productId;
    private String productName;
    private int productPrice;
    private float rating;
    private String image;

    public ProductListItem(String productId, String productName, String image) {
        this.productName = productName;
        this.productId = productId;
        this.image = image;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
