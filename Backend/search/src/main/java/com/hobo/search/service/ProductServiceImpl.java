package com.hobo.search.service;

import com.hobo.search.model.ProductDTO;

import java.util.List;

public interface ProductServiceImpl {

    ProductDTO saveProduct (ProductDTO productDTO);

    ProductDTO getProduct (String id);

    ProductDTO deleteProduct (String id);

    ProductDTO putProduct (ProductDTO productDTO);

    List<ProductDTO> getAllProduct ();

    List<List<ProductDTO>> query(String query);
}
