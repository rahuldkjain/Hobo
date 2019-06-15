package com.hobo.merchant.model;

public class MerchantDTO {

    private int merchantId;
    private String merchantName;
    private String address;
    private String email;
    private long phoneNumber;

    public MerchantDTO(int merchantId, String merchantName, String address, String email, long phoneNumber) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public MerchantDTO() {
    }

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



    @Override
    public String toString() {
        return "MerchantDTO{" +
                "merchantId=" + merchantId +
                ", merchantName='" + merchantName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
