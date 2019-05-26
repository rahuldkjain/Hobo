package com.example.hoboandroid.models.order;

import com.google.gson.annotations.SerializedName;

public class OrderMe {

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private OrderData data;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public void setData(OrderData data){
        this.data = data;
    }

    public OrderData getData(){
        return data;
    }

    public void setError(String error){
        this.error = error;
    }

    public String getError(){
        return error;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "code = '" + code + '\'' +
                        ",data = '" + data + '\'' +
                        ",error = '" + error + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}