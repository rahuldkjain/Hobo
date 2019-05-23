package com.hobo.product.service.SubCategoryImpl;

import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.model.SubCategory;
import com.hobo.product.model.SubCategoryDTO;
import com.hobo.product.repository.SubCategoryRepository;
import com.hobo.product.service.SubCategoryService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository repository;
    @Override
    public SubCategoryDTO getSubCategory(String subCategoryName) {
        SubCategory subCategory = repository.findBySubCategoryName(subCategoryName);
        if(subCategory != null){
            SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
            BeanUtils.copyProperties(subCategory, subCategoryDTO);
            return subCategoryDTO;
        }
        return null;
    }

    @Override
    public SubCategoryDTO deleteSubCategory(String subCategoryName) {
        SubCategory subCategory = repository.findBySubCategoryName(subCategoryName);
        if(subCategory != null){
            SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
            BeanUtils.copyProperties(subCategory, subCategoryDTO);
            repository.delete(subCategory);
            return subCategoryDTO;
        }
        return null;
    }

    @Override
    public SubCategoryDTO createSubCategory(SubCategoryDTO subCategoryDTO) throws ProductAlreadyExists {
        if(repository.exists(subCategoryDTO.getSubCategoryId())){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content Already Exists");
            error.put("message", "Content you are inserting is already present in the database");
            throw new ProductAlreadyExists(error);
        }
        SubCategory subCategory = new SubCategory();
        BeanUtils.copyProperties(subCategoryDTO, subCategory);
        SubCategory result = repository.save(subCategory);

        SubCategoryDTO resultDTO = new SubCategoryDTO();
        BeanUtils.copyProperties(result, resultDTO);
        System.out.println("result: " + result);
        System.out.println("resultDTO: " + resultDTO);
        return resultDTO;
    }

    @Override
    public SubCategoryDTO updateSubCategory(SubCategoryDTO subCategoryDTO) {
        if(repository.exists(subCategoryDTO.getSubCategoryId())){
            SubCategory subCategory = new SubCategory();
            BeanUtils.copyProperties(subCategoryDTO, subCategory);
            repository.save(subCategory);
            return subCategoryDTO;
        }
        return null;
    }

    @Override
    public List<SubCategory> getAllSubCategory(String parentCategory) {
        return repository.findByParentCategory(parentCategory);
    }


}
