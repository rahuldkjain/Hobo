package com.hobo.merchant.entity;

import javax.persistence.*;

@Entity
@Table(name = "merchantproduct")
public class MerchantProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int indexx;
    private int merchantId;
    private int productId;
    private int stock;
    private float price;
    private float productRating;
    private int productsSold;
    private double merchantScore;


    public int getIndexx() {
        return indexx;
    }

    public void setIndexx(int indexx) {
        this.indexx = indexx;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getProductRating() {
        return productRating;
    }

    public void setProductRating(float productRating) {
        this.productRating = productRating;
    }

    public int getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(int productsSold) {
        this.productsSold = productsSold;
    }

    public double getMerchantScore() {
        return merchantScore;
    }

    public void setMerchantScore(double merchantScore) {
        this.merchantScore = merchantScore;
    }

    @Override
    public String toString() {
        return "MerchantProduct{" +
                "indexx=" + indexx +
                ", merchantId=" + merchantId +
                ", productId=" + productId +
                ", stock=" + stock +
                ", price=" + price +
                ", productRating=" + productRating +
                ", productsSold=" + productsSold +
                ", merchantScore=" + merchantScore +
                '}';
    }
}
