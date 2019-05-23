package com.hobo.product.repository;

import com.hobo.product.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    Category findByCategoryName(String categoryName);
}
