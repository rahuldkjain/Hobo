package com.example.hoboandroid.models;
import com.google.gson.annotations.SerializedName;

public class OrderedProduct{

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("productId")
    private int productId;

    @SerializedName("orderId")
    private int orderId;

    @SerializedName("merchantId")
    private int merchantId;

    @SerializedName("productPrice")
    private int productPrice;

    @SerializedName("indexx")
    private int indexx;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getIndexx() {
        return indexx;
    }

    public void setIndexx(int indexx) {
        this.indexx = indexx;
    }
}