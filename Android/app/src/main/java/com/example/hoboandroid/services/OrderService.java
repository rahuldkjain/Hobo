package com.example.hoboandroid.services;

import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.models.OrderedProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderService {

    @GET("/order/getall")
    Call<List<Order>> getOrders(@Query("userId") String userId);


    @GET("/order/getorderdetails")
    Call<List<OrderedProduct>> getOrderDetails(@Query("orderId") Integer orderId,@Query("userId") Integer userId);

    @GET("/order")
    Call<Order> getOrderById(@Query("orderId") int orderId);
}
