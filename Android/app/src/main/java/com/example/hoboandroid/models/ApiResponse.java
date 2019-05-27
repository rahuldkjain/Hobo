package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private T data;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
