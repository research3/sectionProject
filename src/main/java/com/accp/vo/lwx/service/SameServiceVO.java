package com.accp.vo.lwx.service;

public class SameServiceVO {
	private Integer serviceID;	//服务编号
	private String serviceTitle;	//服务标题
	private String serviceCoverImg;	//服务封面图
	private String servicePrice;	//服务价格
	private Integer userID;	//用户编号	
	private String shopName;//店铺名称
	private String shopImg;	//店铺图片
	public Integer getServiceID() {
		return serviceID;
	}
	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}
	public String getServiceTitle() {
		return serviceTitle;
	}
	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}
	public String getServiceCoverImg() {
		return serviceCoverImg;
	}
	public void setServiceCoverImg(String serviceCoverImg) {
		this.serviceCoverImg = serviceCoverImg;
	}
	public String getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
}
