package com.accp.vo.lwx;

public class OrderInfo {
	// 待付款
	private Integer topay;
	// 商家接单
	private Integer receipt;
	// 待确认
	private Integer tbconfirmed;
	// 待评价
	private Integer tbevaluated;

	public Integer getTopay() {
		return topay;
	}

	public void setTopay(Integer topay) {
		this.topay = topay;
	}

	public Integer getReceipt() {
		return receipt;
	}

	public void setReceipt(Integer receipt) {
		this.receipt = receipt;
	}

	public Integer getTbconfirmed() {
		return tbconfirmed;
	}

	public void setTbconfirmed(Integer tbconfirmed) {
		this.tbconfirmed = tbconfirmed;
	}

	public Integer getTbevaluated() {
		return tbevaluated;
	}

	public void setTbevaluated(Integer tbevaluated) {
		this.tbevaluated = tbevaluated;
	}

	public OrderInfo() {
		super();
	}

	public OrderInfo(Integer topay, Integer receipt, Integer tbconfirmed, Integer tbevaluated) {
		super();
		this.topay = topay;
		this.receipt = receipt;
		this.tbconfirmed = tbconfirmed;
		this.tbevaluated = tbevaluated;
	}
}