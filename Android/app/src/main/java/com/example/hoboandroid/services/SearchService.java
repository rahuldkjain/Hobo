package com.example.hoboandroid.services;

import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.models.OrderedProduct;
import com.example.hoboandroid.models.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("/search")
    Call<List<Product>> searchQuery(@Query("query") String query);


    @GET("/suggestions")
    Call<List<Product>> searchSuggestions(@Query("query") String query);
}
