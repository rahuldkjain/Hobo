package com.example.hoboandroid.models.cart;

import com.google.gson.annotations.SerializedName;

public class CartItem{

	@SerializedName("productImage")
	private String productImage;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private int productId;

	@SerializedName("merchantId")
	private int merchantId;

	@SerializedName("userEmail")
	private String userEmail;

	@SerializedName("productName")
	private String productName;

	@SerializedName("productPrice")
	private int productPrice;

	@SerializedName("cartItemId")
	private int cartItemId;

	public void setProductImage(String productImage){
		this.productImage = productImage;
	}

	public String getProductImage(){
		return productImage;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setMerchantId(int merchantId){
		this.merchantId = merchantId;
	}

	public int getMerchantId(){
		return merchantId;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductPrice(int productPrice){
		this.productPrice = productPrice;
	}

	public int getProductPrice(){
		return productPrice;
	}

	public void setCartItemId(int cartItemId){
		this.cartItemId = cartItemId;
	}

	public int getCartItemId(){
		return cartItemId;
	}

	@Override
	public String toString(){
		return
				"Response{" +
						"productImage = '" + productImage + '\'' +
						",quantity = '" + quantity + '\'' +
						",productId = '" + productId + '\'' +
						",merchantId = '" + merchantId + '\'' +
						",userEmail = '" + userEmail + '\'' +
						",productName = '" + productName + '\'' +
						",productPrice = '" + productPrice + '\'' +
						",cartItemId = '" + cartItemId + '\'' +
						"}";
	}
}