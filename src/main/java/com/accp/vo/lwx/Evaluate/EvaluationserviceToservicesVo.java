package com.accp.vo.lwx.Evaluate;

import java.util.Date;

public class EvaluationserviceToservicesVo {
     private String serviceTitle;
     private String serviceCoverImg;
     private String serviceAppraiseContent;
     private Date serviceAppraiseDate;
     
	public EvaluationserviceToservicesVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EvaluationserviceToservicesVo(String serviceTitle, String serviceCoverImg, String serviceAppraiseContent,
			Date serviceAppraiseDate) {
		super();
		this.serviceTitle = serviceTitle;
		this.serviceCoverImg = serviceCoverImg;
		this.serviceAppraiseContent = serviceAppraiseContent;
		this.serviceAppraiseDate = serviceAppraiseDate;
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
	public String getServiceAppraiseContent() {
		return serviceAppraiseContent;
	}
	public void setServiceAppraiseContent(String serviceAppraiseContent) {
		this.serviceAppraiseContent = serviceAppraiseContent;
	}
	public Date getServiceAppraiseDate() {
		return serviceAppraiseDate;
	}
	public void setServiceAppraiseDate(Date serviceAppraiseDate) {
		this.serviceAppraiseDate = serviceAppraiseDate;
	}

}
