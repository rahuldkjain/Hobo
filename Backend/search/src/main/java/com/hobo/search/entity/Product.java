package com.hobo.search.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


@Document(indexName = "hobo_index", type="product")
public class Product {

    @Id
    private String productId;

    @Field()
    private String productName;
    private String description;

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", product_name='" + productName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product_name) {
        this.productName = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
