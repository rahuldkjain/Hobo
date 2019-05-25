package com.example.hoboandroid;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;



public class Api {

//  final static String BASE_URL = "http://127.0.0.1:8080/";
  //final static String BASE_URL = "http://172.16.20.53:8080/";
    static Retrofit retrofit1=null;

    public static Retrofit getclient(String BASE_URL,String endPoint) {
        if(retrofit1==null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(BODY));

            retrofit1 = new Retrofit.Builder()
                    .baseUrl(BASE_URL+endPoint)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();
        }
        return  retrofit1;
    }


    public static Retrofit getclient(String string) {
        if(retrofit1==null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(BODY));

            retrofit1 = new Retrofit.Builder()
                    .baseUrl(string)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();
        }
        return  retrofit1;
    }
}