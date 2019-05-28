package com.example.hoboandroid.services;

import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Merchant;
import com.example.hoboandroid.models.merchantproduct.MerchantProduct;
import com.example.hoboandroid.models.merchantproduct.MerchantProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MerchantService {
    @GET("/merchant/topproductmerchant")
    Call<ApiResponse<Merchant>> getTopMerchant(@Query("productId") Integer productId);

    @GET("/merchant/getProductRating")
    Call<ApiResponse<MerchantProduct>> getProductRating(@Query("productId") String productId);

    @GET("/merchantproduct/productmerchants")
    Call<ApiResponse<List<MerchantProduct>>> getAllMerchants(@Query("productId") Integer productId);



    @GET("/merchantproduct/gettopproductmerchant")
    Call<ApiResponse<MerchantProductResponse>> getTopProductMerchant(@Query("productId") Integer productId);
}
