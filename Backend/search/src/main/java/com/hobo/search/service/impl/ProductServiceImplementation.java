package com.hobo.search.service.impl;

import com.hobo.search.entity.Product;
import com.hobo.search.model.ProductDTO;
import com.hobo.search.repository.ProductRepositoryImpl;
import com.hobo.search.service.ProductServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class ProductServiceImplementation implements ProductServiceImpl {

    @Autowired
    private ProductRepositoryImpl productRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @PostConstruct
    public void before() {
        elasticsearchTemplate.deleteIndex(Product.class);
        elasticsearchTemplate.createIndex(Product.class);
        elasticsearchTemplate.putMapping(Product.class);
        elasticsearchTemplate.refresh(Product.class);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        if( productRepository.exists(product.getProductId())) {
            throw new RuntimeException();
        }
        Product result = productRepository.save(product);
        ProductDTO resultDTO = new ProductDTO();
        BeanUtils.copyProperties(result,resultDTO);
        return resultDTO;
    }

    @Override
    public ProductDTO getProduct(String id) {
        Product result = productRepository.findOne(id);
        ProductDTO resultDTO = new ProductDTO();
        BeanUtils.copyProperties(result, resultDTO);
        return resultDTO;
    }

    @Override
    public ProductDTO deleteProduct(String id) {
        Product product = productRepository.findOne(id);
        productRepository.delete(product);
        ProductDTO result = new ProductDTO();
        BeanUtils.copyProperties(product,result);
        return result;
    }

    @Override
    public ProductDTO putProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product result = productRepository.save(product);
        ProductDTO resultDTO= new ProductDTO();
        BeanUtils.copyProperties(result,resultDTO);
        return resultDTO;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        Iterable<Product> productList = productRepository.findAll();
        List<ProductDTO> resultList = new ArrayList<>();

        for ( Product e: productList) {
            ProductDTO productDTO =new ProductDTO();
            BeanUtils.copyProperties(e,productDTO);
            resultList.add(productDTO);
        }
        return resultList;
    }

    @Override
    public List<List<ProductDTO>> query(String query) {
        List<List<ProductDTO>> result = new ArrayList<>();
        Iterable<Product> productList = productRepository.findByProductNameContaining(query);
        List<ProductDTO> resultList = new ArrayList<>();
        for(Product e: productList) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(e,productDTO);
            resultList.add(productDTO);
        }
        result.add(resultList);
        resultList = new ArrayList<>();
        productList = productRepository.findByDescriptionContaining(query);
        for(Product e: productList) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(e,productDTO);
            resultList.add(productDTO);
        }
        result.add(resultList);
        return result;
    }
}
