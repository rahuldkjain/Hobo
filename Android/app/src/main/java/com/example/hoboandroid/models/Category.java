package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("category_id")
    private int categoryId;

    @SerializedName("category_name")
    private String categoryName;

    @SerializedName("image")
    private String image;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
