package com.hobo.product.model;

import java.util.List;

public class SubCategoryDTO {
    String subCategoryId;
    String subCategoryName;
    String parentCategory;
    String subCategoryImage;
    List subCategoryAttributes;

    public List getSubCategoryAttributes() {
        return subCategoryAttributes;
    }

    public void setSubCategoryAttributes(List subCategoryAttributes) {
        this.subCategoryAttributes = subCategoryAttributes;
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

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getSubCategoryImage() {
        return subCategoryImage;
    }

    public void setSubCategoryImage(String subCategoryImage) {
        this.subCategoryImage = subCategoryImage;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "subCategoryName='" + subCategoryName + '\'' +
                ", parentCategory='" + parentCategory + '\'' +
                ", subCategoryImage='" + subCategoryImage + '\'' +
                '}';
    }
}
