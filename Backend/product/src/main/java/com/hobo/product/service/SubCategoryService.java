package com.hobo.product.service;

import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.model.SubCategory;
import com.hobo.product.model.SubCategoryDTO;

import java.util.List;

public interface SubCategoryService {
    SubCategoryDTO getSubCategory(String subCategoryName);
    SubCategoryDTO deleteSubCategory(String subCategoryName);
    SubCategoryDTO createSubCategory(SubCategoryDTO subCategoryDTO) throws ProductAlreadyExists;
    SubCategoryDTO updateSubCategory(SubCategoryDTO subCategoryDTO);
    List<SubCategory> getAllSubCategory(String parentCategory);
}
