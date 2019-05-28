package com.example.hoboandroid.models.cart;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CartItem{

	@SerializedName("productImage")
	private List<String> productImage;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private int productId;

	@SerializedName("merchantId")
	private int merchantId;

	@SerializedName("userEmail")
	private String userEmail;

	@SerializedName("stock")
	private int stock;

	@SerializedName("productName")
	private String productName;

	@SerializedName("productPrice")
	private int productPrice;

	@SerializedName("cartItemId")
	private int cartItemId;

	@SerializedName("merchantName")
	private String merchantName;

	public void setProductImage(List<String> productImage){
		this.productImage = productImage;
	}

	public List<String> getProductImage(){
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

	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
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

	public void setMerchantName(String merchantName){
		this.merchantName = merchantName;
	}

	public String getMerchantName(){
		return merchantName;
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
						",stock = '" + stock + '\'' +
						",productName = '" + productName + '\'' +
						",productPrice = '" + productPrice + '\'' +
						",cartItemId = '" + cartItemId + '\'' +
						",merchantName = '" + merchantName + '\'' +
						"}";
	}
}