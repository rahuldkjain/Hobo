package com.hobo.search.controller;

import com.hobo.search.model.ProductDTO;
import com.hobo.search.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ProductServiceImpl productService;

    @PostMapping(consumes = {"application/json"})
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO res = new ProductDTO();
        res = productService.saveProduct(productDTO);
        return res;
    }

    @GetMapping
    public ProductDTO getProduct(@RequestParam String id) {
        return productService.getProduct(id);
    }

    @DeleteMapping
    public ProductDTO deleteProduct(@RequestParam String id) {
        return productService.deleteProduct(id);
    }

    @PutMapping(consumes = {"application/json"})
    public ProductDTO putProduct(@RequestBody ProductDTO productDTO) {
        return productService.putProduct(productDTO);
    }

    @GetMapping(value = "/getall")
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping(value = "/suggestion")
    public List<List<ProductDTO>> getQuery(@RequestParam String query) {
        return productService.query(query);
    }

}
