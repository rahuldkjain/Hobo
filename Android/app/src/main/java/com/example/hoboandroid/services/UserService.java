package com.example.hoboandroid.services;

import com.example.hoboandroid.models.Category;
import com.example.hoboandroid.models.SubCategory;
import com.example.hoboandroid.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {


    @POST("/login")
    Call<User> loginByEmailAndPassword(@Query("userId") String userId, @Query("password") String password);

    @POST("/signup")
    Call<User> signup(@Query("user") User user);

    @PUT("/user")
    Call<User>  updateUser(@Query("user") User user);

    @GET("/user")
    Call<User> getUserById(@Query("userId") int userId);



}
