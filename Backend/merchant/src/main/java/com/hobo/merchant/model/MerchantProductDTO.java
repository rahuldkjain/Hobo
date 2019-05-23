package com.hobo.merchant.model;

public class MerchantProductDTO {
    int merchant_id;
    int product_id;
    int stock;
    float price;
    float product_rating;
    int products_sold;

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public float getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(float product_rating) {
        this.product_rating = product_rating;
    }

    public int getProducts_sold() {
        return products_sold;
    }

    public void setProducts_sold(int products_sold) {
        this.products_sold = products_sold;
    }

    @Override
    public String toString() {
        return "MerchantProductDTO{" +
                "merchant_id=" + merchant_id +
                ", product_id=" + product_id +
                ", stock=" + stock +
                ", price=" + price +
                ", product_rating=" + product_rating +
                ", products_sold=" + products_sold +
                '}';
    }
}

