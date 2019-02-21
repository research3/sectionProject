package com.accp.biz.lwx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lwx.OrderDao;
import com.accp.vo.lwx.OrderInfo;
import com.accp.vo.lwx.OrdersVo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class OrderBiz {
	
	@Autowired
	private OrderDao orderDao;
	
	/**
	 * 获取用户订单信息
	 * 
	 * @param userid
	 *            用户编号
	 * @return
	 */
	public OrderInfo queryOrderInfo(int userid) {
		int topay = 0, receipt = 0, tbconfirmed = 0, tbevaluated = 0;
		OrdersVo order = new OrdersVo();
		order.setUserid(userid);
		for (OrdersVo o : orderDao.queryOrderList(order)) {
			switch (o.getOrderstatus()) {
			case 1:
				topay++;
				break;
			case 3:
				receipt++;
				break;
			case 4:
				tbconfirmed++;
				break;
			case 5:
				if (o.getCommentstatus() == 1)
					tbevaluated++;
				break;
			}
		}
		return new OrderInfo(topay, receipt, tbconfirmed, tbevaluated);
	}
	
	
	/**
	 * 分页查询订单列表
	 * 
	 * @param order
	 *            订单
	 * @param page
	 *            页数
	 * @param size
	 *            行数
	 * @return
	 */
	public PageInfo<OrdersVo> queryOrderList(OrdersVo order, int page, int size) {
		PageHelper.startPage(page, size);
		return new PageInfo<OrdersVo>(orderDao.queryOrderList(order));
	}
	
	/**
	 * 根据编号获取订单
	 * 
	 * @param orderid
	 *            订单编号
	 * @return
	 */
	public OrdersVo queryOrderById(String orderid) {
		return orderDao.queryOrderById(orderid);
	}
}
