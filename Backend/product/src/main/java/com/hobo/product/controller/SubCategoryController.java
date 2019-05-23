package com.hobo.product.controller;

import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.model.SubCategory;
import com.hobo.product.model.SubCategoryDTO;
import com.hobo.product.service.SubCategoryImpl.SubCategoryServiceImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubCategoryController {
    @Autowired
    SubCategoryServiceImpl service;

    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");
        return response;
    }

    @GetMapping("/subcategory")
    public JSONObject getSubCategory(@RequestParam String subCategoryName){
        SubCategoryDTO subCategoryDTO = service.getSubCategory(subCategoryName);
        JSONObject response = getJSONResponse(subCategoryDTO);
        return response;
    }

    @PostMapping("/subcategory")
    public JSONObject createSubCategory(@RequestBody SubCategoryDTO subCategoryDTO) throws ProductAlreadyExists {
        SubCategoryDTO subCategory = service.createSubCategory(subCategoryDTO);
        JSONObject response = getJSONResponse(subCategoryDTO);
        return response;
    }

    @PutMapping("/subcategory")
    public JSONObject updateSubCategory(@RequestBody SubCategoryDTO subCategoryDTO){
        SubCategoryDTO subCategory = service.updateSubCategory(subCategoryDTO);
        JSONObject response = getJSONResponse(subCategoryDTO);
        return response;
    }

    @DeleteMapping("/subcategory")
    public JSONObject deleteSubCategory(@RequestParam String subCategoryName){
        SubCategoryDTO subCategoryDTO = service.deleteSubCategory(subCategoryName);
        JSONObject response = getJSONResponse(subCategoryDTO);
        return response;
    }

    @GetMapping("/listsubcategory")
    public JSONObject getAllSubCategory(@RequestParam String parentCategory){
        List<SubCategory> subCategories = service.getAllSubCategory(parentCategory);
        JSONObject response = getJSONResponse(subCategories);
        return response;
    }
}
