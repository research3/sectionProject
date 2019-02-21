package com.accp.vo.lwx;

public class OrdersVo extends com.accp.pojo.Orders{
	// 服务
	private ServicesVo service;

	public ServicesVo getService() {
		return service;
	}

	public void setService(ServicesVo service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "OrdersVo [service=" + service + "]";
	}
	
}
