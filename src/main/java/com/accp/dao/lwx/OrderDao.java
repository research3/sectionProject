package com.accp.dao.lwx;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.vo.lwx.OrdersVo;


public interface OrderDao {
	/**
	 * 查询订单列表
	 * 
	 * @param order
	 *            订单
	 * @return
	 */
	List<OrdersVo> queryOrderList(@Param("order") OrdersVo order);
	
	/**
	 * 根据编号查询订单
	 * 
	 * @param orderid
	 *            编号
	 * @return
	 */
	OrdersVo queryOrderById(@Param("orderid") String orderid);
}
