package com.example.hoboandroid.services;

import com.example.hoboandroid.models.MerchantProduct;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MerchantService {
    @GET("/merchant/topproductmerchant")
    Call<MerchantProduct> getTopMerchant(@Query("productId") String productId);

    //@GET("/merchant/getProductRating")
    //Call<> getProductRating(@Query("productId") String productId);


}
