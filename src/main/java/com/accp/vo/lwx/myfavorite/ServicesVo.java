package com.accp.vo.lwx.myfavorite;

public class ServicesVo {
 
    private String serviceCoverImg;
    private String serviceFuTitle;
    private Integer servicePrice;
    
    
    
	
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
	public ServicesVo( String serviceCoverImg,String serviceFuTitle, Integer servicePrice) {
		super();
		this.serviceCoverImg = serviceCoverImg;
		this.serviceFuTitle = serviceFuTitle;
		this.servicePrice = servicePrice;
	}
	public ServicesVo() {
		super();
		// TODO Auto-generated constructor stub
	} 
}
