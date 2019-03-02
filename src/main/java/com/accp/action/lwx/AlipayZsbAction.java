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
import org.springframework.web.bind.annotation.RequestMapping;

import com.accp.biz.lwx.OrderBiz;
import com.accp.cfg.AlipayConfig;
import com.accp.pojo.Goldnotes;
import com.accp.pojo.User;
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
	/**
	 * 获取订单数据接口
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws AlipayApiException
	 * @throws IOException
	 */
	/*@RequestMapping("viewOrder")
	public void viewOrder(HttpServletRequest req, Model mod, HttpServletResponse rep,
			@RequestParam(value = "goodId", required = true) Integer goodId) throws AlipayApiException, IOException {

		CommonResponse cr = new CommonResponse();
		User cu = ViewSessionManager.getUserSession();
		if (cu == null) { // 需要登录才能买东西
			cr.setMessage("未登录");
			cr.setData(null);
			cr.setCode(3109);
		}
		// 系统下单
		OrderInfo param = new OrderInfo();
		param.setGoodId(goodId);
		payService.alipayOrder(cu, param); // 生成订单信息，根据自己项目改动

		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		GoldnotesBiz goldnotes=goldnotes.addGoldnotes(goldnotes);
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no ="3232";
		// 付款金额，必填
		String total_amount = "434";
		// 订单名称，必填
		String subject ="4343";
		// 商品描述，可空
		String body ="gd";

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();

		rep.setContentType("text/html;charset=" + AlipayConfig.charset);
		rep.getWriter().write(result);// 直接将完整的表单html输出到页面
		rep.getWriter().flush();
		rep.getWriter().close();
	}*/

	/**
	 * 回调路径return_url
	 * 
	 * @param request
	 * @param response
	 * @throws AlipayApiException
	 * @throws UnsupportedEncodingException
	 */
	/*@RequestMapping("return_url.view")
	public String returnUrl(HttpServletRequest request, HttpServletResponse response)
			throws AlipayApiException, UnsupportedEncodingException {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
				AlipayConfig.sign_type);
		if (signVerified) {
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

			request.setAttribute("out_trade_no", out_trade_no);
			request.setAttribute("trade_no", trade_no);
			request.setAttribute("total_amount", total_amount);

		//	log.info("订单处理：系统订单号" + out_trade_no + "支付宝交易号：" + trade_no);
			// 系统处理根据支付宝回调更改订单状态或者其他关联表的数据
			OrderInfo order = payService.findOneByTradeCode(out_trade_no);
			if (order == null) {
				signVerified = false;
				request.setAttribute("signVerified", signVerified);
				request.setAttribute("reason", "商户订单号不存在");
				log.error("系统订单：" + out_trade_no + "不存在。");
			} else {
				if (!order.getMoney().toString().equals(total_amount)) {
					signVerified = false;
					request.setAttribute("signVerified", signVerified);
					request.setAttribute("reason", "付款金额不对");
					return "notify_url";
				}

				if (order.getTradeStatus() == 1) {// 判断当前订单是否已处理，避免重复处理
					log.info("系统订单：" + out_trade_no + "无需重复处理。");
				} else {
					order.setTradeStatus(1);// 修改订单状态为已支付
					Date payedAt = new Date();
					order.setTransactionId(trade_no);
					order.setPayedAt(payedAt);
					payService.payOrder(order);
					log.info("系统订单：" + out_trade_no + "成功支付。");
				}

			}
		} else {
			request.setAttribute("reason", "验签失败");
		}
		request.setAttribute("reason", "验签失败");
		request.setAttribute("signVerified", signVerified);
		return "return_url";
	}*/
	@RequestMapping("viewOrder")
	public void viewOrder(HttpSession session,HttpServletRequest req, Model mod, HttpServletResponse rep,Goldnotes goldnotes) throws AlipayApiException, IOException {
		Integer userId=((User)session.getAttribute("USER")).getUserid();
	
		//获得初始化的AlipayClient
		goldnotes.setUserid(userId);
		goldnotes.setRecorddate(new Date());
		goldnotes.setAuditstatus(5);
		goldnotes.setRecorddescribe("充值金币");
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		biz.addGoldnotes(goldnotes);
		
//		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
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
		Integer userId=((User)session.getAttribute("USER")).getUserid();
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
		if(signVerified) {
			//商户订单号
			String out_trade_no =new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			request.setAttribute("out_trade_no", out_trade_no);
			request.setAttribute("trade_no", trade_no);
			request.setAttribute("total_amount", total_amount);
            Float mount=Float.parseFloat(total_amount);
			biz.updateUserToGoldnotes(mount,out_trade_no,userId,4);
		}else {
			request.setAttribute("reason", "验签失败");
		}
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
