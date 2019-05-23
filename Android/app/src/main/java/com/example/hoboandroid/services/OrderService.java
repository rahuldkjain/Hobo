package com.example.hoboandroid.services;

import com.example.hoboandroid.models.Category;
import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.models.SubCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @GET("/order/getall")
    Call<List<Category>> getOrders(@Query("userId") int userId);


    //@GET("/order/getorderdetails")
    //Call<List<?>>

    @GET("/order")
    Call<Order> getOrderById(@Query("orderId") int orderId);
}
