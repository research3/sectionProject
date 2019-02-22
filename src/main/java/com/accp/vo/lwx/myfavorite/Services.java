package com.accp.vo.lwx.myfavorite;

import java.util.ArrayList;
import java.util.List;

public class Services extends com.accp.pojo.Services{
	private List<ServiceTypeVo> serviceTypeVo = new ArrayList<ServiceTypeVo>(0);

	public List<ServiceTypeVo> getServiceTypeVo() {
		return serviceTypeVo;
	}

	public void setServiceTypeVo(List<ServiceTypeVo> serviceTypeVo) {
		this.serviceTypeVo = serviceTypeVo;
	}

	@Override
	public String toString() {
		return "Services [serviceTypeVo=" + serviceTypeVo + "]";
	}

	
	 
}
