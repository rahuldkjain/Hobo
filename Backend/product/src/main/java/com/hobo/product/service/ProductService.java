package com.hobo.product.service;

import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.exceptions.ProductNotFound;
import com.hobo.product.model.ProductDTO;

public interface ProductService {
    ProductDTO getProduct(String productId) throws ProductNotFound;
    ProductDTO createProduct(ProductDTO productDTO) throws ProductAlreadyExists;
    ProductDTO updateProduct(ProductDTO productDTO);
    ProductDTO deleteProduct(String productId) throws ProductNotFound;
}
