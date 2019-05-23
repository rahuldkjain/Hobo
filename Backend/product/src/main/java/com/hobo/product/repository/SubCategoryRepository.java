package com.hobo.product.repository;

import com.hobo.product.model.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubCategoryRepository extends MongoRepository<SubCategory, String> {
    SubCategory findBySubCategoryName(String subCategoryName);
    List<SubCategory> findByParentCategory(String parentCategory);
}
