package com.accp.action.lwx;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accp.biz.lwx.OrderBiz;
import com.accp.pojo.User;
import com.accp.vo.lwx.OrdersVo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/c/lwx")
public class OrderAction {
	@Autowired
	private OrderBiz orderBiz;
	/**
	 * 以下方法仅查询或跳转页面
	 */

	/**
	 * 查询订单列表
	 * 
	 * @param page
	 *            页数
	 * @param orderid
	 *            模糊订单号
	 * @param status
	 *            订单类型
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/query/list")
	public String queryOrderList(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "") String orderid, @RequestParam(defaultValue = "") Integer status,
			@RequestParam(required = false) Integer commentstatus, Model model, HttpSession session) {
//		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		Integer userId=25;
		OrdersVo order = new OrdersVo();
		order.setUserid(userId);
		order.setOrderid(orderid);
		order.setOrderstatus(status);
		order.setCommentstatus(commentstatus);
		PageInfo<OrdersVo> pageInfo = orderBiz.queryOrderList(order, page, 10);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("orderInfo", orderBiz.queryOrderInfo(userId));
		return "grzx-order";
	}
	
	/**
	 * 查询订单详情
	 * 
	 * @param orderid
	 *            订单编号
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/query/detail")
	public String queryOrderDetail(@RequestParam(required = true) String orderid, Model model) {
		model.addAttribute("order", orderBiz.queryOrderById(orderid));
		return "grzx-order-detail";
	}
}
