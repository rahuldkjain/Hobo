package com.hobo.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderproduct")
public class OrderProductEntity {
    @Id
    Integer indexx;
    Integer orderId;
    Integer productId;
    Integer merchantId;
    Integer quantity;
    Integer productPrice;

    public Integer getIndexx() {
        return indexx;
    }

    public void setIndexx(Integer indexx) {
        this.indexx = indexx;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "OrderProductEntity{" +
                "indexx=" + indexx +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", merchantId=" + merchantId +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                '}';
    }
}
