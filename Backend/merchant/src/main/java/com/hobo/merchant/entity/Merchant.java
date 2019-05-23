package com.hobo.merchant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="merchant")
public class Merchant {
    @Id
    int merchant_id;

    String merchant_name;
    float merchant_rating;
    String description;
    String address;
    String email;
    long phone_number;
    int merchant_score;

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public float getMerchant_rating() {
        return merchant_rating;
    }

    public void setMerchant_rating(float merchant_rating) {
        this.merchant_rating = merchant_rating;
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

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public int getMerchant_score() {
        return merchant_score;
    }

    public void setMerchant_score(int merchant_score) {
        this.merchant_score = merchant_score;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchant_id=" + merchant_id +
                ", merchant_name='" + merchant_name + '\'' +
                ", merchant_rating=" + merchant_rating +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone_number=" + phone_number +
                ", merchant_score=" + merchant_score +
                '}';
    }
}

