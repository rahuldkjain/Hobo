package com.example.hoboandroid.models.product;

import com.example.hoboandroid.models.Attributes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

	public String getSubCategory(){
		return subCategory;
	}

	public void setSubCategory(String subCategory){
		this.subCategory = subCategory;
	}

	public List<String> getProductImage(){
		return productImage;
	}

	public void setProductImage(List<String> productImage){
		this.productImage = productImage;
	}

	public String getProductId(){
		return productId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductBrand(){
		return productBrand;
	}

	public void setProductBrand(String productBrand){
		this.productBrand = productBrand;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public Attributes getAttributes(){
		return attributes;
	}

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public String getCategory(){
		return category;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	@Override
 	public String toString(){
		return 
			"Category{" +
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