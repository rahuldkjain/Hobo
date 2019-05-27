package com.example.hoboandroid;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;



public class Api {


    static Retrofit retrofit1=null;

    public static Retrofit getclient(String BASE_URL,String endPoint) {
        if(retrofit1==null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(BODY));

            retrofit1 = new Retrofit.Builder()
                    .baseUrl(BASE_URL+endPoint)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit1;
    }


    public static Retrofit getclient(String string) {

            return  new Retrofit.Builder()
                    .baseUrl(string)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    }
}