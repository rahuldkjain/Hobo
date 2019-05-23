package com.example.hoboandroid.models;

import org.json.JSONObject;
import com.google.gson.annotations.SerializedName;

public class Product {

	@SerializedName("“description”")
	private String description;

	@SerializedName("“category”")
	private String category;


	@SerializedName("“productId”")
	private String productId;

	@SerializedName("“subCategory”")
	private String subCategory;

	@SerializedName("“productName”")
	private String productName;

	@SerializedName("“attributes”")
	private JSONObject attributes;

	private String productRating;

	public String getProductRating() {
		return productRating;
	}

	public void setProductRating(String productRating) {
		this.productRating = productRating;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setSubCategory(String subCategory){
		this.subCategory = subCategory;
	}

	public String getSubCategory(){
		return subCategory;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setAttributes(JSONObject attributes){
		this.attributes = attributes;
	}

	public JSONObject getAttributes(){
		return attributes;
	}

	@Override
 	public String toString(){
		return
			"Product{" +
			"“description” = '" + description + '\'' +
			",“category” = '" + category + '\'' +
			",“productId” = '" + productId + '\'' +
			",“subCategory” = '" + subCategory + '\'' +
			",“productName” = '" + productName + '\'' +
			",“attributes” = '" + attributes + '\'' +
			"}";
		}
}