package com.hobo.product.controller;

import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.model.Category;
import com.hobo.product.model.CategoryDTO;
import com.hobo.product.service.CategoryService;
import com.hobo.product.service.CategoryServiceImpl.CategoryServiceImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryServiceImpl service;

    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");
        return response;
    }

    @GetMapping("/category")
    public JSONObject getCategory(@RequestParam String categoryName){
        CategoryDTO categoryDTO = service.getCategory(categoryName);
        JSONObject response = getJSONResponse(categoryDTO);
        return response;
    }

    @PostMapping("/category")
    public JSONObject createCategory(@RequestBody CategoryDTO categoryDTO) throws ProductAlreadyExists {
        CategoryDTO data = service.createCategory(categoryDTO);
        JSONObject response = getJSONResponse(data);
        return response;
    }

    @PutMapping("/category")
    public JSONObject updateCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO category = service.updateCategory(categoryDTO);
        JSONObject response = getJSONResponse(category);
        return response;
    }

    @DeleteMapping("/category")
    public JSONObject deleteCategory(@RequestParam String categoryName){
        CategoryDTO categoryDTO = service.deleteCategory(categoryName);
        JSONObject response = getJSONResponse(categoryDTO);
        return response;
    }

    @RequestMapping("/listcategory")
    public JSONObject getAllCategory(){
        List<Category> categories = service.getAllCategory();
        JSONObject response = getJSONResponse(categories);
        response.replace("message", "categories fetched successfully");
        return response;
    }
}
