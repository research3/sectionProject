package com.accp.vo.lwx.myfavorite;

public class UserToServicesVo {
	   private String userName;
	    private String shopName;
	    private String signature;
	    private String shopImg;
	    private String serviceCoverImg;
	    private String serviceFuTitle;
	    private Integer servicePrice;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getShopName() {
			return shopName;
		}
		public void setShopName(String shopName) {
			this.shopName = shopName;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		public String getShopImg() {
			return shopImg;
		}
		public void setShopImg(String shopImg) {
			this.shopImg = shopImg;
		}
		public String getServiceCoverImg() {
			return serviceCoverImg;
		}
		public void setServiceCoverImg(String serviceCoverImg) {
			this.serviceCoverImg = serviceCoverImg;
		}
		public String getServiceFuTitle() {
			return serviceFuTitle;
		}
		public void setServiceFuTitle(String serviceFuTitle) {
			this.serviceFuTitle = serviceFuTitle;
		}
		public Integer getServicePrice() {
			return servicePrice;
		}
		public void setServicePrice(Integer servicePrice) {
			this.servicePrice = servicePrice;
		}
		public UserToServicesVo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public UserToServicesVo(String userName, String shopName, String signature, String shopImg,
				String serviceCoverImg, String serviceFuTitle, Integer servicePrice) {
			super();
			this.userName = userName;
			this.shopName = shopName;
			this.signature = signature;
			this.shopImg = shopImg;
			this.serviceCoverImg = serviceCoverImg;
			this.serviceFuTitle = serviceFuTitle;
			this.servicePrice = servicePrice;
		}
}
