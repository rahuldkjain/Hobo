package com.example.hoboandroid.models.product;

import com.example.hoboandroid.models.Attributes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListItem {

    private int productId;
    private String productName;
    private int productPrice;
    private float rating;
    private String image;

    public ProductListItem(int productId, String productName, String image) {
        this.productName = productName;
        this.productId = productId;
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductListItem{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", rating=" + rating +
                ", image='" + image + '\'' +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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
