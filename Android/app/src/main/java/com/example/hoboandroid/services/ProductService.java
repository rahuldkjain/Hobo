package com.example.hoboandroid.services;

import com.example.hoboandroid.models.Category;
import com.example.hoboandroid.models.Product;
import com.example.hoboandroid.models.ResponseFromApi;
import com.example.hoboandroid.models.SubCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductService {
    @GET("/listcategory")
    Call<ResponseFromApi> getCategories();

    @POST("/listsubcategory")
    Call<List<SubCategory>> getSubCategory(@Query("category") String category);

    @GET("/product/getall")
    Call<ResponseFromApi> getAllProducts();

    @GET("/category")
    Call<List<Product>> getProrductsByCatAndSub(@Query("category") String category,@Query("subCategory") String subCategory);


    @GET("/get")
    Call<Product> getProductById(@Query("productId") int productId);

}
