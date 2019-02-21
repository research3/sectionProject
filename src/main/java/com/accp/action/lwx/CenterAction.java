package com.accp.action.lwx;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accp.biz.lwx.MyFavoriteBiz;
import com.accp.biz.lwx.OrderBiz;
import com.accp.pojo.User;
import com.accp.vo.lwx.OrdersVo;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/c/lwx")
public class CenterAction {

	@Autowired
	private OrderBiz orderBiz;
	@Autowired
	private MyFavoriteBiz myFavoriteBiz;
	
	/**
	 * 个人中心主页
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/center/home")
	public String centerHome(Model model, HttpSession session) {
//		User user = (User) session.getAttribute("USER");
		//后期加上的（去除）
		User user = myFavoriteBiz.getUser(25);
		OrdersVo order = new OrdersVo();
//		order.setUserid(user.getUserid());
		order.setUserid(25);
		model.addAttribute("list", orderBiz.queryOrderList(order, 1, 10).getList());
//		model.addAttribute("orderInfo", orderBiz.queryOrderInfo(user.getUserid()));
		model.addAttribute("orderInfo", orderBiz.queryOrderInfo(25));
		model.addAttribute("user", user);
		return "grzx-index";
	}
}
