package com.example.hoboandroid.services;

import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.models.OrderedProduct;
import com.example.hoboandroid.models.order.OrderProductMe;
import com.example.hoboandroid.models.order.OrderMe;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @GET("/orders/getall")
    Call<ApiResponse<List<Order>>> getOrders(@Query("userId") String userId);


    @GET("/orders/getorderdetails")
    Call<ApiResponse<List<OrderedProduct>>> getOrderDetails(@Query("orderId") Integer orderId,@Query("userId") String userId);

    //@GET("/orders")
    //Call<ApiResponse<Order>> getOrderById(@Query("orderId") Integer orderId);

    @GET("/orders")
    Call<OrderMe> getOrderById(@Query("orderId") Integer orderId);

    @POST("/orders")
    Call<OrderMe> saveOrder(@Body RequestBody params);

    @POST("/orderproduct")
    Call<OrderProductMe> saveProduct(@Body RequestBody params);



}
