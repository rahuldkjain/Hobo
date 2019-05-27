package com.example.hoboandroid.services;

import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Cart;
import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.models.OrderedProduct;
import com.example.hoboandroid.models.cart.CartItem;
import com.example.hoboandroid.models.order.OrderData;
import com.example.hoboandroid.models.order.OrderProductMe;
import com.example.hoboandroid.models.order.OrderMe;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface OrderService {

    @GET("/orders/getall")
    Call<ApiResponse<List<OrderData>>> getOrders(@Query("userId") String userId);


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

    @GET("/cart/usercart")
    Call<ApiResponse<List<CartItem>>> getCartItems(@Query("emailId")String userEmailId);

    @POST("/cart")
    Call<ApiResponse<CartItem>> createCartItem(@Body RequestBody params);

    @PUT("/cart")
    Call<ApiResponse<CartItem>> updateCartItem(CartItem cartItem);

    @DELETE("/cart")
    Call<ApiResponse<CartItem>> deleteCartItem(Integer cartItemId);
}
