package com.hobo.product.service.ProductServiceImpl;


import com.hobo.product.exceptions.ProductAlreadyExists;
import com.hobo.product.exceptions.ProductNotFound;
import com.hobo.product.model.Product;
import com.hobo.product.model.ProductDTO;
import com.hobo.product.repository.ProductRepository;
import com.hobo.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;


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
        System.out.println("Result: " + result);
        return resultDTO;

    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws ProductAlreadyExists {
        if(repository.exists(productDTO.getProductId())){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content Already Exists");
            error.put("message", "Content you are inserting is already present in the database");
            throw new ProductAlreadyExists(error);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product result = repository.save(product);

        ProductDTO resultDTO = new ProductDTO();
        BeanUtils.copyProperties(result, resultDTO);
        System.out.println("result: " + result);
        System.out.println("resultDTO: " + resultDTO);
        return resultDTO;
    }

    @Override
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
}
