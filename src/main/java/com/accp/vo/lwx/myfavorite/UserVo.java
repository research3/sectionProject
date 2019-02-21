package com.accp.vo.lwx.myfavorite;

import java.util.ArrayList;
import java.util.List;

public class UserVo {
	    private String userName;
	    private String shopName;
	    private String signature;
	    private String shopImg;
	    private List<ServicesVo>servicesVo=new ArrayList<ServicesVo>(0);
	    
	    private float merchantLevel;
	    private int merchantExp;
	    private int creditScore;
	    private List<ServiceTypeVo> serviceTypeVo = new ArrayList<ServiceTypeVo>(0);
	    
		public UserVo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public UserVo(String userName, String shopName, String signature, String shopImg, List<ServicesVo> servicesVo) {
			super();
			this.userName = userName;
			this.shopName = shopName;
			this.signature = signature;
			this.shopImg = shopImg;
			this.servicesVo = servicesVo;
		}
		public List<ServicesVo> getServicesVo() {
			return servicesVo;
		}
		public void setServicesVo(List<ServicesVo> servicesVo) {
			this.servicesVo = servicesVo;
		}
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
		public float getMerchantLevel() {
			return merchantLevel;
		}
		public void setMerchantLevel(float merchantLevel) {
			this.merchantLevel = merchantLevel;
		}
		public int getMerchantExp() {
			return merchantExp;
		}
		public void setMerchantExp(int merchantExp) {
			this.merchantExp = merchantExp;
		}
		public int getCreditScore() {
			return creditScore;
		}
		public void setCreditScore(int creditScore) {
			this.creditScore = creditScore;
		}
		public List<ServiceTypeVo> getServiceTypeVo() {
			return serviceTypeVo;
		}
		public void setServiceTypeVo(List<ServiceTypeVo> serviceTypeVo) {
			this.serviceTypeVo = serviceTypeVo;
		}
		@Override
		public String toString() {
			return "UserVo [userName=" + userName + ", shopName=" + shopName + ", signature=" + signature + ", shopImg="
					+ shopImg + ", servicesVo=" + servicesVo + ", merchantLevel=" + merchantLevel + ", merchantExp="
					+ merchantExp + ", creditScore=" + creditScore + ", serviceTypeVo=" + serviceTypeVo + "]";
		}
	
		
}
