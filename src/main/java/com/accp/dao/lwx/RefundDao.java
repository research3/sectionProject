package com.accp.dao.lwx;

import org.apache.ibatis.annotations.Param;

import com.accp.vo.lwx.RefundVo;


public interface RefundDao {
	/**
	 * 获取退款详单
	 * 
	 * @param orderId
	 *            订单编号
	 * @return
	 */
	RefundVo queryRefundByOrderId(@Param("orderid") String orderId);
}
