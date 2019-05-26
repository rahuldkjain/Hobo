package com.example.hoboandroid.services;

import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Data;
import com.example.hoboandroid.models.LoginData;
import com.example.hoboandroid.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {


    @POST("login")
    Call<ApiResponse> loginByEmailAndPassword(@Body LoginData loginData);

    @POST("user")
    Call<ApiResponse> signup(@Body Data data);




}
