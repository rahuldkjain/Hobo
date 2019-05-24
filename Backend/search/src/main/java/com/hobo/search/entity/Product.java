package com.hobo.search.entity;

import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


@Document(indexName = "hobo_index", type="product")
public class Product {

    @Id
    private String productId;
    private String productName;
    private String description;
    private String category;
    private String subCategory;
    private String productImage;
    private String productBrand;
    @NestedField
    private JSONObject attributes;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
