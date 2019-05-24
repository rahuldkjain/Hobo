package com.example.hoboandroid.models.category;
import com.google.gson.annotations.SerializedName;

public class Category {

	@SerializedName("categoryImage")
	private String categoryImage;

	@SerializedName("categoryName")
	private String categoryName;

	@SerializedName("categoryId")
	private String categoryId;

	public String getCategoryImage(){
		return categoryImage;
	}

	public void setCategoryImage(String categoryImage){
		this.categoryImage = categoryImage;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	@Override
 	public String toString(){
		return 
			"Category{" +
			"categoryImage = '" + categoryImage + '\'' + 
			",categoryName = '" + categoryName + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}