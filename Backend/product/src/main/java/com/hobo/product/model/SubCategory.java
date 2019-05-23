package com.hobo.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = SubCategory.COLLECTION_NAME)
public class SubCategory {
    public static final String COLLECTION_NAME="subcategory";

    @Id
    String subCategoryId;
    String subCategoryName;
    String parentCategory;
    String subCategoryImage;

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public static String getCollectionName() {
        return COLLECTION_NAME;
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
