package com.accp.vo.lwx.service;

public class SerRecommendVO {
	private Integer userID;
	private String userName;
	private String userImgPath;
	private String shopImg;
	private String selfIntroduction;
	private float merchantLevel;
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImgPath() {
		return userImgPath;
	}
	public void setUserImgPath(String userImgPath) {
		this.userImgPath = userImgPath;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public String getSelfIntroduction() {
		return selfIntroduction;
	}
	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}
	public float getMerchantLevel() {
		return merchantLevel;
	}
	public void setMerchantLevel(float merchantLevel) {
		this.merchantLevel = merchantLevel;
	}
}
