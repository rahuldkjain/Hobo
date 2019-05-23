package com.hobo.search.repository;

import com.hobo.search.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepositoryImpl extends ElasticsearchRepository<Product,String> {
    List<Product> findByProductName(String query);

    List<Product> findByDescription(String query);

    Iterable<Product> findByProductNameOrDescription(String query);

    Iterable<Product> findByProductNameLike(String query);

    Iterable<Product> findByDescriptionLike(String query);

    Iterable<Product> findByProductNameContaining(String query);

    Iterable<Product> findByDescriptionContaining(String query);
}
