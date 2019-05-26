package com.example.hoboandroid.services;

import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {


    @POST("/login")
    Call<ApiResponse<User>> loginByEmailAndPassword(@Query("userId") String userId, @Query("password") String password);

    @POST("/signup")
    Call<ApiResponse<User>> signup(@Query("user") User user);

    @PUT("/user")
    Call<User>  updateUser(@Query("user") User user);

    @GET("/user")
    Call<User> getUserById(@Query("userId") int userId);



}
