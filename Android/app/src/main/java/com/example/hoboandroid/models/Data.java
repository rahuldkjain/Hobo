package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Data {

	@SerializedName("pincode")
	private int pincode;

	@SerializedName("password")
	private String password;

	@SerializedName("phoneNumber")
	private long  phoneNumber;

	@SerializedName("gender")
	private String gender;

	@SerializedName("address2")
	private String address2;

	@SerializedName("city")
	private String city;

	@SerializedName("address1")
	private String address1;

	@SerializedName("name")
	private String name;

	@SerializedName("emailId")
	private String emailId;

	@SerializedName("dateOfBirth")
	private Date dateOfBirth;

	@SerializedName("state")
	private String state;

	public void setPincode(int pincode){
		this.pincode = pincode;
	}

	public int getPincode(){
		return pincode;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhoneNumber(long phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public long getPhoneNumber(){
		return phoneNumber;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setAddress2(String address2){
		this.address2 = address2;
	}

	public String getAddress2(){
		return address2;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setAddress1(String address1){
		this.address1 = address1;
	}

	public String getAddress1(){
		return address1;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmailId(String emailId){
		this.emailId = emailId;
	}

	public String getEmailId(){
		return emailId;
	}

	public void setDateOfBirth(Date dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth(){
		return dateOfBirth;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"pincode = '" + pincode + '\'' + 
			",password = '" + password + '\'' + 
			",phoneNumber = '" + phoneNumber + '\'' + 
			",gender = '" + gender + '\'' + 
			",address2 = '" + address2 + '\'' + 
			",city = '" + city + '\'' + 
			",address1 = '" + address1 + '\'' + 
			",name = '" + name + '\'' + 
			",emailId = '" + emailId + '\'' + 
			",dateOfBirth = '" + dateOfBirth + '\'' + 
			",state = '" + state + '\'' + 
			"}";
		}
}