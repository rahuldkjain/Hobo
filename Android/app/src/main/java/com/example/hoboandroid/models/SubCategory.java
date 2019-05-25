package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

public class SubCategory {

    @SerializedName("subCategoryImage")
    private String subCategoryImage;

    @SerializedName("parentCategory")
    private String parentCategory;

    @SerializedName("subCategoryId")
    private String subCategoryId;

    @SerializedName("subCategoryName")
    private String subCategoryName;

    @SerializedName("attributes")
    private Attributes attributes;

    public String getSubCategoryImage() {
        return subCategoryImage;
    }

    public void setSubCategoryImage(String subCategoryImage) {
        this.subCategoryImage = subCategoryImage;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
