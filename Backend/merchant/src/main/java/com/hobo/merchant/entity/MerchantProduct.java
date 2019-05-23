package com.hobo.merchant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchantproduct")
public class MerchantProduct {
    @Id
    int merchant_id;
    int product_id;
    int stock;
    float price;
    float product_rating;
    int products_sold;


}
