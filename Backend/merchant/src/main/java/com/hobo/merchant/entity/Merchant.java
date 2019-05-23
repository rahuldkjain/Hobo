package com.hobo.merchant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="merchant")
public class Merchant {

    @Id
    @Column(name = "merchantId")
    Integer merchantId;

    String merchantName;
    float merchantRating;
    String description;
    String address;
    String email;
    long phoneNumber;
    int merchantScore;

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMerchantScore() {
        return merchantScore;
    }

    public void setMerchantScore(int merchantScore) {
        this.merchantScore = merchantScore;
    }

    public float getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(float merchantRating) {
        this.merchantRating = merchantRating;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantId=" + merchantId +
                ", merchantName='" + merchantName + '\'' +
                ", merchantRating=" + merchantRating +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", merchantScore=" + merchantScore +
                '}';
    }
}

