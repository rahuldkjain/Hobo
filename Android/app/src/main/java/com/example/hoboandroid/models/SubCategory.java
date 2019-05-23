package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

public class SubCategory {

    @SerializedName("sub_category_id")
    private int subCategoryId;

    @SerializedName("sub_category_name")
    private String subCategoryName;

    @SerializedName("parent_category")
    private String parentCategory;

    @SerializedName("sub_category_image")
    private String subCategoryImage;


    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

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
}
