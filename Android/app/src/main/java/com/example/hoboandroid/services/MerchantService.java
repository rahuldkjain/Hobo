package com.example.hoboandroid.services;

import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Merchant;
import com.example.hoboandroid.models.MerchantProduct;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MerchantService {
    @GET("/merchant/topproductmerchant")
    Call<ApiResponse<Merchant>> getTopMerchant(@Query("productId") Integer productId);

    @GET("/merchant/getProductRating")
    Call<ApiResponse<MerchantProduct>> getProductRating(@Query("productId") String productId);

    @GET("/merchant/getallmerchant")
    Call<MerchantProduct> getAllMerchants(@Query("productId") String productId);


    @GET("/merchant/getallmerchant")
    Call<ApiResponse<MerchantProduct>> getMerchantProduct(@Query("merchantId") String merchantId,@Query("productId") String productId);
}
