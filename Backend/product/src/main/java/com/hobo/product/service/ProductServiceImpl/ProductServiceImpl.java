package com.hobo.product.service.ProductServiceImpl;


import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.exceptions.ProductNotFound;
import com.hobo.product.model.Category;
import com.hobo.product.model.Product;
import com.hobo.product.model.ProductDTO;
import com.hobo.product.repository.ProductRepository;
import com.hobo.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public ProductDTO getProduct(String productId) throws ProductNotFound {
        if(!repository.exists(productId)){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");

            throw new ProductNotFound(error);
        }

        Product result = repository.findOne(productId);
        ProductDTO resultDTO = new ProductDTO();
        BeanUtils.copyProperties(result, resultDTO);
        return resultDTO;

    }

    @Override
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) throws ProductAlreadyExists {
        if(repository.exists(productDTO.getProductId())){
            JSONObject error = new JSONObject();
            throw new ProductAlreadyExists("Product already Exists");
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product result = repository.save(product);

        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8085/search";
        HttpEntity<ProductDTO> payload = new HttpEntity<>(productDTO);
        ProductDTO resultDTO = restTemplate.postForObject(url, payload, ProductDTO.class);
        BeanUtils.copyProperties(result, resultDTO);
        return resultDTO;
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        if (repository.exists(product.getProductId())){
            Product result = repository.save(product);
            ProductDTO resultDTO = new ProductDTO();
            BeanUtils.copyProperties(result, resultDTO);
            return resultDTO;
        }

        return null;
    }

    @Override
    @Transactional
    public ProductDTO deleteProduct(String productId) throws ProductNotFound {
        if(!repository.exists(productId)){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new ProductNotFound(error);
        }
        ProductDTO productDTO = getProduct(productId);
        if (productDTO != null){
            Product product = new Product();
            BeanUtils.copyProperties(productDTO, product);
            repository.delete(product);
        }

        return productDTO;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();
        return products;
    }


    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        products = repository.findByCategory(category);
        return products;
    }

    @Override
    public List<Product> getProductsBySubCategory(String subCategory) {
        List<Product> products = new ArrayList<>();
        products = repository.findBySubCategory(subCategory);
        return products;
    }

}
