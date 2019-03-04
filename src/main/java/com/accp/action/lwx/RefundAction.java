package com.accp.action.lwx;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.biz.lwx.OrderBiz;
import com.accp.biz.lwx.RefundBiz;
import com.accp.pojo.User;
import com.accp.vo.lwx.OrdersVo;
import com.accp.vo.lwx.RefundVo;

@Controller
@RequestMapping("/c/lwx")
public class RefundAction {
	
	@Autowired
	private OrderBiz orderBiz;
	@Autowired
	private RefundBiz refundBiz;
	
	/**
	 * 查看退款详情
	 * 
	 * @param orderid
	 *            订单编号
	 * @param model
	 * @return
	 */
	@RequestMapping("/refund/detail")
	public String refundDetail(@RequestParam(required = true) String orderid, Model model) {
		model.addAttribute("order", orderBiz.queryOrderById(orderid));
		model.addAttribute("refund", refundBiz.queryRefundByOrderId(orderid));
		return "grzx-refund-detail";
	}
	
	/**
	 * 申请管理员介入
	 * 
	 * @param orderid
	 *            订单编号
	 * @return
	 */
	@RequestMapping("/refund/applyadmin")
	public String applyAdmin(@RequestParam(required = true) String orderid) {
		OrdersVo order = new OrdersVo();
		order.setOrderid(orderid);
		order.setRefundstatus(3);
		RefundVo refund = new RefundVo();
		refund.setOrderid(orderid);
		refund.setAdminstatus(1);
		refundBiz.applyAdmin(order, refund);
		return "redirect:/c/lhy/refund/detail?orderid=" + orderid;
	}

	/**
	 * 查询收到的退款列表
	 * 
	 * @param page
	 *            页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/refund/list")
	public String refundList(@RequestParam(defaultValue = "1") Integer page, Model model, HttpSession session) {
		Integer userid = ((User) session.getAttribute("USER")).getUserid();
		model.addAttribute("pageInfo", refundBiz.queryRefundList(userid, page, 10));
		return "sjzx-refund";
	}

	/**
	 * 查询收到的退款详情
	 * 
	 * @param orderid
	 *            订单编号
	 * @param model
	 * @return
	 */
	@RequestMapping("/refund/sjdetail")
	public String refundSJDetail(@RequestParam(required = true) String orderid, Model model) {
		model.addAttribute("order", orderBiz.queryOrderById(orderid));
		model.addAttribute("refund", refundBiz.queryRefundByOrderId(orderid));
		return "sjzx-refund-detail";
	}

	/**
	 * 同意退款
	 * 
	 * @param orderid
	 * @return
	 */
	@RequestMapping("/refund/ok")
	@ResponseBody
	public boolean refundOk(@RequestParam(required = true) String orderid) {
		try {
			OrdersVo orderInfo = orderBiz.queryOrderById(orderid);
			OrdersVo order = new OrdersVo();
			order.setOrderid(orderid);
			order.setRefundstatus(5);
			RefundVo refund = new RefundVo();
			refund.setOrderid(orderid);
			refund.setAuditstatus(2);
			refund.setAudittime(new Date());
			refundBiz.refundOk(orderInfo, order, refund);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 拒绝退款理由填写
	 * 
	 * @param orderid
	 *            订单编号
	 * @param model
	 * @return
	 */
	@RequestMapping("/refund/why")
	public String refundWhy(@RequestParam(required = true) String orderid, Model model) {
		model.addAttribute("orderid", orderid);
		return "sjzx-refund-why";
	}

	/**
	 * 拒绝退款
	 * 
	 * @param refund
	 *            退款
	 * @param model
	 * @return
	 */
	@RequestMapping("/refund/no")
	public String refundNo(RefundVo refund, Model model) {
		OrdersVo order = new OrdersVo();
		order.setOrderid(refund.getOrderid());
		order.setRefundstatus(2);
		refund.setAudittime(new Date());
		refund.setAuditstatus(3);
		refundBiz.refundNo(order, refund);
		return "redirect:/c/lhy/refund/list";
	}

	/**
	 * 查询我的退款列表
	 * 
	 * @param page
	 *            页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/refund/mjlist")
	public String refundMJList(@RequestParam(defaultValue = "1") Integer page, Model model, HttpSession session) {
		Integer userid = ((User) session.getAttribute("USER")).getUserid();
		model.addAttribute("pageInfo", refundBiz.queryMyRefundList(userid, page, 10));
		return "grzx-refund";
	}
}
