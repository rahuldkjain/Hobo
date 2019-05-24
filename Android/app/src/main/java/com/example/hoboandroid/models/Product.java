package com.example.hoboandroid.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Product{

	@SerializedName("subCategory")
	private String subCategory;

	@SerializedName("productImage")
	private List<String> productImage;

	@SerializedName("productId")
	private String productId;

	@SerializedName("productBrand")
	private String productBrand;

	@SerializedName("description")
	private String description;

	@SerializedName("attributes")
	private Attributes attributes;

	@SerializedName("category")
	private String category;

	@SerializedName("productName")
	private String productName;

	public void setSubCategory(String subCategory){
		this.subCategory = subCategory;
	}

	public String getSubCategory(){
		return subCategory;
	}

	public void setProductImage(List<String> productImage){
		this.productImage = productImage;
	}

	public List<String> getProductImage(){
		return productImage;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setProductBrand(String productBrand){
		this.productBrand = productBrand;
	}

	public String getProductBrand(){
		return productBrand;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public Attributes getAttributes(){
		return attributes;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"subCategory = '" + subCategory + '\'' + 
			",productImage = '" + productImage + '\'' + 
			",productId = '" + productId + '\'' + 
			",productBrand = '" + productBrand + '\'' + 
			",description = '" + description + '\'' + 
			",attributes = '" + attributes + '\'' + 
			",category = '" + category + '\'' + 
			",productName = '" + productName + '\'' + 
			"}";
		}
}