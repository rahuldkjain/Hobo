package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

public class Merchant {
    @SerializedName("merchant_id")
    private String merchantId;

    @SerializedName("merchant_name")
    private String merchantName;


    @SerializedName("merchant_rating")
    private String merchantRating;

    @SerializedName("description")
    private String description;

    @SerializedName("address")
    private String address;

    @SerializedName("email")
    private String email;

    @SerializedName("phone_number")
    private String phone_number;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(String merchantRating) {
        this.merchantRating = merchantRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
