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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
    @Transactional
    public SubCategoryDTO createSubCategory(SubCategoryDTO subCategoryDTO) throws ProductAlreadyExists {
        if(repository.exists(subCategoryDTO.getSubCategoryId())){
            throw new ProductAlreadyExists("Data already Stored");
        }
        SubCategory subCategory = new SubCategory();
        BeanUtils.copyProperties(subCategoryDTO, subCategory);
        SubCategory result = repository.save(subCategory);

        SubCategoryDTO resultDTO = new SubCategoryDTO();
        BeanUtils.copyProperties(result, resultDTO);
        return resultDTO;
    }

    @Override
    @Transactional
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
