package com.hobo.product.controller;

import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.exceptions.ProductNotFound;
import com.hobo.product.model.Product;
import com.hobo.product.model.ProductDTO;
import com.hobo.product.service.ProductServiceImpl.ProductServiceImpl;
import com.mongodb.util.JSON;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl service;

    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");
        return response;
    }

    @GetMapping("/product")
    public JSONObject getProduct(@RequestParam String productId) throws ProductNotFound {

        ProductDTO productDTO = service.getProduct(productId);
        JSONObject response = getJSONResponse(productDTO);
        response.replace("message", "success", "fetching successful");

        return response;
    }

    @PostMapping(value = "/product", consumes = "application/json")
    public JSONObject createProduct(@RequestBody ProductDTO productDTO) throws ProductAlreadyExists {
        ProductDTO product = service.createProduct(productDTO);
        JSONObject response = getJSONResponse(product);
        response.replace("message", "success", "insertion completed successfully.");
        return response;
    }

    @PutMapping(value = "/product", consumes = "application/json")
    public JSONObject updateProduct(@RequestBody ProductDTO productDTO){
        ProductDTO product = service.updateProduct(productDTO);
        JSONObject response = getJSONResponse(product);
        response.replace("message", "success", "update completed successfully");
        return response;
    }

    @DeleteMapping("/product")
    public JSONObject deleteProduct(@RequestParam String productId) throws ProductNotFound {
        ProductDTO productDTO = service.deleteProduct(productId);
        JSONObject response = getJSONResponse(productDTO);
        response.replace("message", "success", "deletion completed successfully");
        return response;
    }

    @RequestMapping("/product/getall")
    public JSONObject getAllProducts(){
        List<Product> product= service.getAllProducts();
        JSONObject response = getJSONResponse(product);
        response.replace("message", "success", "fetching all products succeeded");
        return response;
    }

    @RequestMapping("/product/category")
    public JSONObject getProductsByCategory(@RequestParam String category){
        List<Product> product= service.getProductsByCategory(category);
        JSONObject response = getJSONResponse(product);
        return response;
    }

    @RequestMapping("/product/subcategory")
    public JSONObject getProductsBySubCategory(@RequestParam String subCategory){
        List<Product> product= service.getProductsBySubCategory(subCategory);
        JSONObject response = getJSONResponse(product);
        return response;
    }

}
