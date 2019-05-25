package com.example.hoboandroid.services;

import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.category.Category;
import com.example.hoboandroid.models.category.ResponseCategory;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.models.product.ResponseProductsList;
import com.example.hoboandroid.models.SubCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductService {

    @GET("/listcategory")
    Call<ApiResponse<List<Category>>> getCategories();

    @GET("/listsubcategory")
    Call<ApiResponse<List<SubCategory>>> getSubCategory(@Query("parentCategory") String category);

    @GET("/product/getall")
    Call<ApiResponse<List<Product>>> getAllProducts();

    @GET("/category")
    Call<List<Product>> getProductsByCatAndSub(@Query("category") String category,@Query("subCategory") String subCategory);

    @GET("/product")
    Call<ApiResponse<Product>> getProductById(@Query("productId") Integer productId);

}
