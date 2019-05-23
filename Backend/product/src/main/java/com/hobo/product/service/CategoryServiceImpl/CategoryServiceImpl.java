package com.hobo.product.service.CategoryServiceImpl;

import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.model.Category;
import com.hobo.product.model.CategoryDTO;
import com.hobo.product.repository.CategoryRepository;
import com.hobo.product.service.CategoryService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository repository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws ProductAlreadyExists {
        if(repository.exists(categoryDTO.getCategoryId())){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content Already Exists");
            error.put("message", "Content you are inserting is already present in the database");
            throw new ProductAlreadyExists(error);
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        Category result = repository.save(category);

        CategoryDTO resultDTO = new CategoryDTO();
        BeanUtils.copyProperties(result, resultDTO);
        System.out.println("result: " + result);
        System.out.println("resultDTO: " + resultDTO);
        return resultDTO;
    }

    @Override
    public CategoryDTO getCategory(String categoryName) {
        Category category = repository.findByCategoryName(categoryName);
        if (category != null){
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(category, categoryDTO);
            return categoryDTO;
        }
        return null;
    }

    @Override
    public CategoryDTO deleteCategory(String categoryName) {
        Category category = repository.findByCategoryName(categoryName);
        if (category != null){
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(category, categoryDTO);
            repository.delete(category.getCategoryId());
            return  categoryDTO;
        }
        return null;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        if(repository.exists(categoryDTO.getCategoryId())){
            Category category = new Category();
            BeanUtils.copyProperties(categoryDTO, category);
            repository.save(category);
            return categoryDTO;
        }
        return null;
    }


    @Override
    public List<Category> getAllCategory() {
        return repository.findAll();
    }
}
