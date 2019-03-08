package com.accp.action.lwx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accp.action.config.AlipayConfig;
import com.accp.biz.lwx.OrderBiz;
import com.accp.biz.lwx.UserBiz;
import com.accp.pojo.Goldnotes;
import com.accp.pojo.User;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
@Controller
@RequestMapping("/c/lwx")
public class AlipayZsbAction {// response
	@Autowired
	private OrderBiz biz;
	
	@Autowired
	private UserBiz userbiz;

	@RequestMapping("viewOrder")
	public void viewOrder(HttpSession session,HttpServletRequest req, Model mod, HttpServletResponse rep,Goldnotes goldnotes,Integer uuid) throws AlipayApiException, IOException {
		String sessionId = req.getSession().getId();
		System.out.println("sessionId前：==="+sessionId);
		req.setAttribute("uuid", uuid);
		System.out.println("uuid:==="+uuid);
		Integer userId=((User)session.getAttribute("USER")).getUserid();
		//获得初始化的AlipayClient
		goldnotes.setUserid(userId);
		goldnotes.setRecorddate(new Date());
		goldnotes.setAcquisitionmode(5);
		goldnotes.setRecorddescribe("充值金币");
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		biz.addGoldnotes(goldnotes);
		
//		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url+"?userid="+userId);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no =goldnotes.getRecordid().toString();
		//付款金额，必填
		String total_amount =goldnotes.getRecordinandout().toString();//recordInAndOut
		//订单名称，必填
	    String subject =goldnotes.getUserid().toString()+"账号充值";
		//商品描述，可空
		String body ="";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		String result = alipayClient.pageExecute(alipayRequest).getBody();
		rep.setContentType("text/html;charset=" + AlipayConfig.charset);
		rep.getWriter().write(result);// 直接将完整的表单html输出到页面
		rep.getWriter().flush();
		rep.getWriter().close();
		//输出
	}
	
	@RequestMapping("return_url")
	public String returnUrl( HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws AlipayApiException, UnsupportedEncodingException {
		Integer aa=Integer.parseInt(request.getParameter("userid"));
		System.out.println(aa);
		session.setAttribute("USER", userbiz.queryUserById(aa));
		
		
		
		Integer userId=((User)session.getAttribute("USER")).getUserid();
//		User user=(User)session.getAttribute("USER");
//		System.out.println("user==="+user.toString());
//		Integer userId = user.getUserid(); 
		System.out.println(((User)session.getAttribute("USER")));
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
//		if(signVerified) {
			//商户订单号
			String out_trade_no =new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			
			request.setAttribute("out_trade_no", out_trade_no);
			request.setAttribute("trade_no", trade_no);
			request.setAttribute("total_amount", total_amount);
			
			request.setAttribute("userId", userId);
			
            Float mount=Float.parseFloat(total_amount);
			biz.updateUserToGoldnotes(mount,out_trade_no,userId,1);
			System.out.println("验签成功");
//		}else {
//			request.setAttribute("reason", "验签失败");
//			System.out.println("验签失败");
//		}
		request.setAttribute("signVerified", signVerified);
		return "redirect:/c/lwx/goldnotesQueryAll";
	}
	
	
	@RequestMapping("notify_url")
	public String notifyUrl(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws AlipayApiException, UnsupportedEncodingException {

		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr =  new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			
			
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			request.setAttribute("out_trade_no", out_trade_no);
			request.setAttribute("trade_no", trade_no);
			request.setAttribute("total_amount", total_amount);
			
		}else {//验证失败
			request.setAttribute("reason", "验签失败");
		}
		return "notify_url";
	}
}
