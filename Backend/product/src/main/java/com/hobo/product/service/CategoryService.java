package com.hobo.product.service;


import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.model.CategoryDTO;
import com.hobo.product.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO) throws ProductAlreadyExists;
    CategoryDTO getCategory(String categoryName);
    CategoryDTO deleteCategory(String categoryName);
    CategoryDTO updateCategory(CategoryDTO categoryDTO);
    List<Category> getAllCategory();
}
