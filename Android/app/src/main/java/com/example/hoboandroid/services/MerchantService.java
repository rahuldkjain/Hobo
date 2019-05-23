package com.example.hoboandroid.services;

import com.example.hoboandroid.models.Merchant;
import com.example.hoboandroid.models.MerchantProduct;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MerchantService {
    @GET("/merchant/topproductmerchant")
    Call<Merchant> getTopMerchant(@Query("productId") String productId);

    //@GET("/merchant/getProductRating")
    //Call<> getProductRating(@Query("productId") String productId);

    @GET("/merchant/getallmerchant")
    Call<MerchantProduct> getAllMerchants(@Query("productId") String productId);


}
