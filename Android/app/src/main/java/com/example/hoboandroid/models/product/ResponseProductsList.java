package com.example.hoboandroid.models.product;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseProductsList {

	@SerializedName("code")
	private String code;

	@SerializedName("data")
	private List<Product> data;

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

	public void setData(List<Product> data){
		this.data = data;
	}

	public List<Product> getData(){
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
			"ResponseProductsList{" +
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}