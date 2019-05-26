package com.hobo.order.model;

public class OrderProductDTO {
    private int indexx;
    private int orderId;
    private int productId;
    private int merchantId;
    private int quantity;
    private int productPrice;

    @Override
    public String toString() {
        return "OrderProductDTO{" +
                "indexx=" + indexx +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", merchantId=" + merchantId +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                '}';
    }

    public int getIndexx() {
        return indexx;
    }

    public void setIndexx(int indexx) {
        this.indexx = indexx;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
