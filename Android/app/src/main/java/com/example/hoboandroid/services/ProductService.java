package com.example.hoboandroid.services;

import com.example.hoboandroid.models.Category;
import com.example.hoboandroid.models.Product;
import com.example.hoboandroid.models.SubCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductService {
    @GET("/product/listcategory")
    Call<List<Category>> getCategories();

    @GET("/product/getall")
    Call<List<Category>> getAllProducts();

    @GET("/product/category")
    Call<List<Product>> getProrductsByCatAndSub(@Query("category") String category, @Query("subCategory") String subCategory);


    @GET("/product/get")
    Call<Product> getProductById(@Query("productId") int productId);

}
