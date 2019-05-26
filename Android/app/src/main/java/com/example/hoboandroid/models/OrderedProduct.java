package com.example.hoboandroid.models;
import com.google.gson.annotations.SerializedName;
public class OrderedProduct{
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("productId")
    private String productId;
    @SerializedName("orderId")
    private String orderId;
    @SerializedName("merchantId")
    private String merchantId;
    @SerializedName("productPrice")
    private String productPrice;
    @SerializedName("indexx")
    private String indexx;
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }
    public String getQuantity(){
        return quantity;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }
    public String getProductId(){
        return productId;
    }
    public void setOrderId(String orderId){
        this.orderId = orderId;
    }
    public String getOrderId(){
        return orderId;
    }
    public void setMerchantId(String merchantId){
        this.merchantId = merchantId;
    }
    public String getMerchantId(){
        return merchantId;
    }
    public void setProductPrice(String productPrice){
        this.productPrice = productPrice;
    }
    public String getProductPrice(){
        return productPrice;
    }
    public void setIndexx(String indexx){
        this.indexx = indexx;
    }
    public String getIndexx(){
        return indexx;
    }
    @Override
    public String toString(){
        return
                "OrderedProduct{" +
                        "quantity = '" + quantity + '\'' +
                        ",productId = '" + productId + '\'' +
                        ",orderId = '" + orderId + '\'' +
                        ",merchantId = '" + merchantId + '\'' +
                        ",productPrice = '" + productPrice + '\'' +
                        ",indexx = '" + indexx + '\'' +
                        "}";
    }
}