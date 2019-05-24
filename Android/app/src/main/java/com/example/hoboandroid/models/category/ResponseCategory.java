package com.example.hoboandroid.models.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCategory {

	@SerializedName("code")
	private String code;

	@SerializedName("data")
	private List<Category> data;

	@SerializedName("error")
	private String error;

	@SerializedName("message")
	private String message;

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public List<Category> getData(){
		return data;
	}

	public void setData(List<Category> data){
		this.data = data;
	}

	public String getError(){
		return error;
	}

	public void setError(String error){
		this.error = error;
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCategory{" +
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}