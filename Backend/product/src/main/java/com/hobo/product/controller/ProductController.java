package com.hobo.product.controller;

import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.exceptions.ProductNotFound;
import com.hobo.product.model.Product;
import com.hobo.product.model.ProductDTO;
import com.hobo.product.service.ProductService;
import com.hobo.product.service.ProductServiceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl service;

    @GetMapping("/product")
    public ProductDTO getProduct(@RequestParam String productId) throws ProductNotFound {
        return service.getProduct(productId);
    }

    @PostMapping(value = "/product", consumes = "application/json")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) throws ProductAlreadyExists {
        return service.createProduct(productDTO);
    }

    @PutMapping(value = "/product", consumes = "application/json")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        return service.updateProduct(productDTO);
    }

    @DeleteMapping("/product")
    public ProductDTO deleteProduct(@RequestParam String productId) throws ProductNotFound {
        return service.deleteProduct(productId);
    }
}
