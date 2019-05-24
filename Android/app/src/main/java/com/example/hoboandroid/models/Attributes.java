package com.example.hoboandroid.models;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("ScreenResolution")
	private String screenResolution;

	@SerializedName("USB")
	private String uSB;

	@SerializedName("ScreenType")
	private String screenType;

	@SerializedName("HDMI")
	private String hDMI;

	@SerializedName("ROM")
	private String rOM;

	@SerializedName("Processor")
	private String processor;

	@SerializedName("Color")
	private String color;

	@SerializedName("Weight")
	private String weight;

	@SerializedName("Resolution")
	private String resolution;

	@SerializedName("Display")
	private String display;

	@SerializedName("RAM")
	private String rAM;

	@SerializedName("Graphics")
	private String graphics;

	public void setScreenResolution(String screenResolution){
		this.screenResolution = screenResolution;
	}

	public String getScreenResolution(){
		return screenResolution;
	}

	public void setUSB(String uSB){
		this.uSB = uSB;
	}

	public String getUSB(){
		return uSB;
	}

	public void setScreenType(String screenType){
		this.screenType = screenType;
	}

	public String getScreenType(){
		return screenType;
	}

	public void setHDMI(String hDMI){
		this.hDMI = hDMI;
	}

	public String getHDMI(){
		return hDMI;
	}

	public void setROM(String rOM){
		this.rOM = rOM;
	}

	public String getROM(){
		return rOM;
	}

	public void setProcessor(String processor){
		this.processor = processor;
	}

	public String getProcessor(){
		return processor;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setWeight(String weight){
		this.weight = weight;
	}

	public String getWeight(){
		return weight;
	}

	public void setResolution(String resolution){
		this.resolution = resolution;
	}

	public String getResolution(){
		return resolution;
	}

	public void setDisplay(String display){
		this.display = display;
	}

	public String getDisplay(){
		return display;
	}

	public void setRAM(String rAM){
		this.rAM = rAM;
	}

	public String getRAM(){
		return rAM;
	}

	public void setGraphics(String graphics){
		this.graphics = graphics;
	}

	public String getGraphics(){
		return graphics;
	}

	@Override
 	public String toString(){
		return 
			"Attributes{" + 
			"screenResolution = '" + screenResolution + '\'' + 
			",uSB = '" + uSB + '\'' + 
			",screenType = '" + screenType + '\'' + 
			",hDMI = '" + hDMI + '\'' + 
			",rOM = '" + rOM + '\'' + 
			",processor = '" + processor + '\'' + 
			",color = '" + color + '\'' + 
			",weight = '" + weight + '\'' + 
			",resolution = '" + resolution + '\'' + 
			",display = '" + display + '\'' + 
			",rAM = '" + rAM + '\'' + 
			",graphics = '" + graphics + '\'' + 
			"}";
		}
}