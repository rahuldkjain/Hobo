package com.example.hoboandroid.models;

public class ProductBriefModel {

        private int productId;
        private String imageId;
        private String productName;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String image_id) {
        this.imageId = image_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ProductBriefModel{" +
                "productId=" + productId +
                ", imageId='" + imageId + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
