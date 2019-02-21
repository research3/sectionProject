package com.accp.vo.lwx;

import com.accp.pojo.User;

public class RefundVo extends com.accp.pojo.Refund {
	// 订单
	private OrdersVo order;
	// 买家
	private UserVo user;

	public User getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public OrdersVo getOrder() {
		return order;
	}

	public void setOrder(OrdersVo order) {
		this.order = order;
	}
}