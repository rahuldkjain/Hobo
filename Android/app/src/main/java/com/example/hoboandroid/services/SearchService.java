package com.example.hoboandroid.services;

import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.product.Product;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("/search/suggestions")
    Call<ApiResponse<JSONObject>> searchQuery(@Query("query") String query);


    @GET("/suggestions")
    Call<ApiResponse<List<Product>>> searchSuggestions(@Query("query") String query);
}
