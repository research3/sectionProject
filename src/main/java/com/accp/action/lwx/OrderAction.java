package com.accp.action.lwx;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accp.biz.lwx.MerchantEnterAndServiceBiz;
import com.accp.biz.lwx.OrderBiz;
import com.accp.biz.lwx.RefundBiz;
import com.accp.biz.lwx.UserBiz;
import com.accp.pojo.Banktype;
import com.accp.pojo.Complainttype;
import com.accp.pojo.Evaluationservice;
import com.accp.pojo.Goldnotes;
import com.accp.pojo.Integralrecord;
import com.accp.pojo.Logistics;
import com.accp.pojo.Orders;
import com.accp.pojo.Post;
import com.accp.pojo.Putforward;
import com.accp.pojo.Resouroe;
import com.accp.pojo.Servicelevel;
import com.accp.pojo.Services;
import com.accp.pojo.Servicetype;
import com.accp.pojo.Sharea;
import com.accp.pojo.User;
import com.accp.util.WlliusUtil;
import com.accp.util.file.Upload;
import com.accp.vo.lwx.OrdersVo;
import com.accp.vo.lwx.RefundVo;
import com.accp.vo.lwx.Evaluate.EvaluateVo;
import com.accp.vo.lwx.Evaluate.EvaluationserviceToservicesVo;
import com.accp.vo.lwx.merchant.ServicesVo;
import com.accp.vo.lwx.myfavorite.UserToServicesVo;
import com.accp.vo.lwx.myfavorite.UserVo;
import com.accp.vo.lwx.service.AdvertisementVO;
import com.accp.vo.lwx.service.EsLevelVO;
import com.accp.vo.lwx.service.HomePostVO;
import com.accp.vo.lwx.service.SameServiceVO;
import com.accp.vo.lwx.service.SerRecommendVO;
import com.accp.vo.lwx.service.SerReserveVO;
import com.accp.vo.lwx.service.ServiceDetailInfo;
import com.accp.vo.lwx.service.ServiceMerchantInfo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/c/lwx")
public class OrderAction {
	@Autowired
	private OrderBiz orderBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private MerchantEnterAndServiceBiz biz;

	/**
	 * 确认支付订单
	 * 
	 * @param orderid
	 *            订单编号
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/pay/ok")
	public String payOk(@RequestParam(required = true) String orderid, HttpSession session) {
		// 用户扣款，更新订单状态，付款时间
		// 交易额，金币记录，站内信商家
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		OrdersVo order = orderBiz.queryOrderById(orderid);
		OrdersVo updateOrder = new OrdersVo();
		updateOrder.setOrderid(orderid);
		updateOrder.setOrderstatus(2);
		updateOrder.setPaymenttime(new Date());
		orderBiz.payOrder(order.getSmallplan(), userId, updateOrder);
		return "redirect:/c/lwx/order/query/list";
	}

	/**
	 * 取消订单
	 * 
	 * @param page
	 *            页数
	 * @param orderid
	 *            模糊订单号
	 * @param updateid
	 *            订单编号
	 * @return
	 */
	@RequestMapping("/order/cancel")
	public String cancel(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "") String orderid, @RequestParam(required = true) String updateid) {
		OrdersVo order = new OrdersVo();
		order.setOrderid(updateid);
		order.setOrderstatus(6);
		orderBiz.cancelOrder(order);
		return "redirect:/c/lwx/order/query/list?page=" + page + "&orderid=" + orderid;
	}

	/**
	 * 确认完成
	 * 
	 * @param page
	 *            页数
	 * @param orderid
	 *            模糊订单号
	 * @param updateid
	 *            订单编号
	 * @return
	 */
	@RequestMapping("/order/ok")
	public String ok(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "") String orderid,
			@RequestParam(required = true) String updateid) {
		// 商家入款，更新订单状态，完成时间
		// 金币记录，站内信商家
		OrdersVo order = orderBiz.queryOrderById(updateid);
		OrdersVo updateOrder = new OrdersVo();
		updateOrder.setOrderid(updateid);
		updateOrder.setOrderstatus(5);
		updateOrder.setCompletetime(new Date());
		updateOrder.setCommentstatus(1);
		updateOrder.setRefundstatus(0);
		orderBiz.ok(order.getSmallplan(), order.getService().getUser().getUserid(), updateOrder);
		return "redirect:/c/lwx/order/query/list?page=" + page + "&orderid=" + orderid;
	}

	/**
	 * 确认评价
	 * 
	 * @param evaluate
	 *            评价
	 * @param orderid
	 *            订单编号
	 * @return
	 */
	@RequestMapping("/order/evaluate/ok")
	public String evaluateOk(EvaluateVo evaluate, String orderid, HttpSession session) {
		// 新增评价信息，修改订单评价状态
		// 站内信
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		OrdersVo order = orderBiz.queryOrderById(orderid);
		evaluate.setServiceid(order.getService().getServiceid());
		evaluate.setUserid(userId);
		evaluate.setServiceappraisedate(new Date());
		OrdersVo updateOrder = new OrdersVo();
		updateOrder.setOrderid(orderid);
		updateOrder.setCommentstatus(2);
		orderBiz.evaluateOk(evaluate, updateOrder);
		return "redirect:/c/lwx/EvaluationService";
	}

	/**
	 * 确认申请退款
	 * 
	 * @param refund
	 *            退款
	 * @param file
	 *            文件
	 * @param model
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/order/refund/ok")
	public String refundOk(RefundVo refund, MultipartFile file, Model model, HttpSession session)
			throws IllegalStateException, IOException {
		// 新增退款信息，修改订单退款状态
		// 站内信
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		refund.setPoint(1);
		refund.setUserid(userId);
		refund.setApplicationtime(new Date());
		if (file != null && !file.isEmpty()) {
			String fileName = Upload.uploadFile(file);
			refund.setRefundimg(fileName);
		}
		refund.setAuditstatus(1);
		OrdersVo order = new OrdersVo();
		order.setOrderid(refund.getOrderid());
		order.setRefundstatus(1);
		orderBiz.refundok(refund, order);
		return "redirect:/c/lwx/refund/detail?orderid=" + refund.getOrderid();
	}

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
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
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

	/**
	 * 支付订单
	 * 
	 * @param orderid
	 *            订单编号
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/pay")
	public String pay(@RequestParam(required = true) String orderid, Model model, HttpSession session) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		model.addAttribute("user", userBiz.queryUserById(userId));
		model.addAttribute("order", orderBiz.queryOrderById(orderid));
		return "grzx-order-pay";
	}

	/**
	 * 评价订单
	 * 
	 * @param orderid
	 *            订单编号
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/evaluate")
	public String evaluate(@RequestParam(required = true) String orderid, Model model) {
		model.addAttribute("order", orderBiz.queryOrderById(orderid));
		return "grzx-order-evaluate";
	}

	/**
	 * 申请退款
	 * 
	 * @param orderid
	 *            订单号
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/refund")
	public String refund(@RequestParam(required = true) String orderid, Model model) {
		model.addAttribute("order", orderBiz.queryOrderById(orderid));
		return "grzx-order-refund";
	}

	/**
	 * 
	 * =======================
	 * 
	 * 
	 */

	/**
	 * 查询当前用户所有订单信息
	 * 
	 * @param session
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/order/queryAllOrder", method = RequestMethod.GET)
	public String queryAllOrdes(HttpSession session, Model model, Integer orderStatus, Integer refundstatus,
			String orderID, Integer pageNum, Integer pageSize) {
		Integer userID = ((User) session.getAttribute("USER")).getUserid();
		model.addAttribute("pageInfo",
				orderBiz.queryUserOrder(userID, orderStatus, refundstatus, orderID, pageNum, pageSize));
		return "/sjzx-order.html";
	}

	/**
	 * 查询类型
	 * 
	 * @param stid
	 * @return
	 */
	@RequestMapping(value = "/order/querySerType", method = RequestMethod.GET)
	@ResponseBody
	public Servicetype querySerType(Integer stid) {
		return orderBiz.querySerType(stid);
	}

	/**
	 * 修改状态 并发送系统消息
	 * 
	 * @param orderStatus
	 * @param orderID
	 * @return
	 */
	@RequestMapping(value = "/order/updateOrders", method = RequestMethod.GET)
	public String updateOrders(HttpSession session, Integer orderStatus, String orderID, Integer userID) {
		orderBiz.updateOrders(orderStatus, orderID);
		String content = "";
		switch (orderStatus) {
		case 3:
			content = "您的订单" + orderID + ",商家已接单,请进入个人中心查看";
			break;
		case 4:
			content = "您的订单" + orderID + ",商家已提供服务,请进入个人中心查看";
			break;
		case 7:
			content = "您的订单" + orderID + ",商家已取消,请进入个人中心查看";
			break;
		default:
			System.out.println("多余状态");
			break;
		}
		userBiz.saveXtxx(userID, content);
		return "redirect:/c/lwx/order/queryAllOrder?orderStatus=0&refundstatus=-1&pageNum=1&pageSize=5&orderID=";
	}

	/**
	 * 查询所有订单信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/order/queryCountOrder", method = RequestMethod.GET)
	@ResponseBody
	public List<Orders> queryCountOrder(HttpSession session) {
		Integer userID = ((User) session.getAttribute("USER")).getUserid();
		return orderBiz.queryCountOrder(userID);
	}

	/**
	 * 查询订单详情
	 * 
	 * @param model
	 * @param orderID
	 * @return
	 */
	@RequestMapping(value = "/order/queryAOrder", method = RequestMethod.GET)
	public String queryAOrder(Model model, String orderID) {
		model.addAttribute("order", orderBiz.queryAOrder(orderID));
		return "/sjrz-order-deatil.html";
	}

	/**
	 * 
	 * 
	 * ==========================
	 * 
	 */

	/**
	 * 添加金币提现记录流向表
	 * 
	 * @param model
	 * @param session
	 * @param putforward
	 * @return
	 */
	@PostMapping("addPutforWard")
	public String addGoldnotes(Model model, HttpSession session, Putforward putforward) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		putforward.setSubmittime(new Date());
		putforward.setUserid(userId);

		orderBiz.addPutforWard(putforward);
		return "redirect:/c/lwx/goldnotesQueryAll";
	}

	/**
	 * 添加金币充值记录流向表
	 * 
	 * @param model
	 * @param session
	 * @param goldnotes
	 * @return
	 */
	@PostMapping("addGoldnotes")
	public String addGoldnotes(Model model, HttpSession session, Goldnotes goldnotes) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		goldnotes.setUserid(userId);
		goldnotes.setRecorddate(new Date());
		goldnotes.setAcquisitionmode(5);
		orderBiz.addGoldnotes(goldnotes);
		return "redirect:/c/lwx/goldnotesQueryAll";
	}
	

	/**
	 * 查询物流记录
	 * 
	 * @param model
	 * @param session
	 * @param p
	 * @param s
	 * @param logistics
	 * @return
	 */
	@GetMapping("getListLogistics")
	public String getListLogistics(Model model, HttpSession session, Integer p, Integer s, Logistics logistics) {
		if (p == null)
			p = 1;
		if (s == null)
			s = 6;
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		logistics.setUserid(userId);

		PageInfo<Logistics> pageInfo = orderBiz.getListLogistics(p, s, logistics);
		model.addAttribute("PAGE_INFO", pageInfo);
		return "grzx-logistics";
	}

	/**
	 * 查询商品服务评价记录表
	 * 
	 * @param model
	 * @param session
	 * @param p
	 * @param s
	 * @param evaluationService
	 * @return
	 */
	@GetMapping("EvaluationService")
	public String getListEvaluationService(Model model, HttpSession session, Integer p, Integer s,
			Evaluationservice evaluationService) {
		if (p == null)
			p = 1;
		if (s == null)
			s = 6;
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		evaluationService.setUserid(userId);
		PageInfo<EvaluationserviceToservicesVo> pageInfo = orderBiz.getListEvaluationService(p, s, evaluationService);
		model.addAttribute("PAGE_INFO", pageInfo);
		return "grzx-comments";
	}

	/**
	 * 查询银行类别表
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("getListBankType")
	public String getListBankType(Model model, HttpSession session) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		User user = orderBiz.getUser(userId);
		if (user.getUsermoney() == null) {
			user.setUsermoney((float) 0);
		}
		List<Banktype> list = orderBiz.getListBankType();
		model.addAttribute("List", list);
		model.addAttribute("USER", user);
		return "jinb-tixt";
	}

	/**
	 * 添加物流记录
	 * 
	 * @param model
	 * @param session
	 * @param logistics
	 * @param imgFile
	 * @return
	 */
	@PostMapping("addLogistics")
	public String addLogistics(Model model, HttpSession session, Logistics logistics, MultipartFile[] imgFile,
			String areaid11, String areaid22, String areaid33, String areaid4, String shareaid11, String shareaid22,
			String shareaid33, String shareaid4) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		logistics.setUserid(userId);

		try {
			List<String> imgUrls = new ArrayList<>(0);
			for (int i = 0; i < imgFile.length; i++) {
				imgUrls.add(Upload.uploadFile(imgFile[i]));
			}
			int a = 1;
			for (String url : imgUrls) {
				switch (a) {
				case 1:
					logistics.setImg1(url);
					break;
				case 2:
					logistics.setImg2(url);
					break;
				case 3:
					logistics.setImg3(url);
					break;
				case 4:
					logistics.setImg4(url);
					break;
				case 5:
					logistics.setImg5(url);
					break;
				default:
					break;
				}
				a++;
			}

		} catch (Exception e) {
			// TOD O Auto-generated catch block
			e.printStackTrace();
		}
		logistics.setCollectgoodsaddr(shareaid11 + shareaid22 + shareaid33 + shareaid4);
		logistics.setUseraddr(areaid11 + areaid22 + areaid33 + areaid4);
		;
		logistics.setOrdertime(new Date());
		logistics.setAuditstatus(1);
		String orderid = WlliusUtil.Getnum();
		logistics.setOrderid(orderid);
		orderBiz.addLogistics(logistics);
		return "redirect:/c/lwx/getListLogistics";
	}

	/**
	 * 查询物流记录详情
	 * 
	 * @param model
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping("getLogistics")
	public String getLogistics(Model model, HttpSession session, Integer id, Integer auditstatus) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		if (id == null) {
			id = 1;
		}
		Logistics logistics = orderBiz.getLogistics(userId, id);
		model.addAttribute("logistics", logistics);
		if (auditstatus == 1) {
			return "wl-xianq";// 待支付
		} else if (auditstatus == 2) {
			return "wl-shz";// 审核中
		} else if (auditstatus == 3) {
			return "wl-sh";// 审核通过
		} else if (auditstatus == 4) {
			return "wl-EMS";// 已发国际EMS
		} else {
			return "wl-wcsh";// 完成收货
		}
	}

	/**
	 * 查询物流支付页面
	 * 
	 * @param model
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping("getLogisticsByPrice")
	public String getLogisticsByPrice(Model model, HttpSession session, Integer id) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		if (id == null) {
			id = 1;
		}
		User users = orderBiz.getUser(userId);
		if (users.getUsermoney() == null) {
			users.setUsermoney((float) 0);
		}
		Logistics logistics = orderBiz.getLogistics(userId, id);
		model.addAttribute("Logistics", logistics);
		model.addAttribute("USER", users);
		if (users.getUsermoney() == null) {
			return "wu_zhif";
		} else {
			if (users.getUsermoney() < logistics.getPrice()) {
				return "wu_zhif";
			} else {
				return "wl-zf";
			}
		}

	}

	/**
	 * 查询地址
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("getShAreaById")
	public String getShAreaById(Model model, HttpSession session) {
		List<Sharea> Sharea = orderBiz.getShAreaById(0);
		model.addAttribute("ShArea", Sharea);
		return "wu-fwl";
	}

	/**
	 * 查询地址省市
	 * 
	 * @param model
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping("getShAreaBy")
	@ResponseBody
	public List<Sharea> getShAreaBy(Model model, HttpSession session, Integer id) {
		return orderBiz.getShAreaById(id);
	}

	/**
	 * 金币充值
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("getLogisticsById")
	public String getLogisticsById(Model model, HttpSession session) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		User user = orderBiz.getUser(userId);
		if (user.getUsermoney() == null) {
			user.setUsermoney((float) 0.0);
		}
		model.addAttribute("USER", user);
		return "jinb-index";
	}

	/**
	 * 物流支付状态
	 * 
	 * @param model
	 * @param session
	 * @param logisticsid
	 * @param userprice
	 * @return
	 */
	@GetMapping("updlogistics")
	@ResponseBody
	public Map<String, String> updlogistics(Model model, HttpSession session, Integer logisticsid, Float userprice) {
		Map<String, String> message = new HashMap<String, String>();
		try {
			Integer userId = ((User) session.getAttribute("USER")).getUserid();
			orderBiz.updUser(userprice, userId, logisticsid);
			message.put("code", "200");
			message.put("msg", "ok");
		} catch (Exception ex) {
			message.put("code", "500");
			message.put("msg", ex.getMessage());
		}
		return message;
	}

	@PostMapping("updateLogistics")
	public String updateLogistics(Model model, HttpSession session, Logistics logistics) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		logistics.setUserid(userId);
		logistics.setNumbertime1(new Date());
		logistics.setAuditstatus(4);
		orderBiz.updatedLogistics(logistics);
		return "redirect:/c/lwx/getListLogistics";
	}

	@GetMapping("xiugai")
	public String xiugai(Model model, HttpSession session, Integer id) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		Logistics logistics = orderBiz.getLogistics(userId, id);

		List<Sharea> Sharea = orderBiz.getShAreaById(0);
		model.addAttribute("l", logistics);
		model.addAttribute("ShArea", Sharea);
		return "diz";
	}

	@PostMapping("updat")
	public String updat(Model model, HttpSession session, Logistics logistics, String shareaid11, String shareaid22,
			String shareaid33, String shareaid4) {
		Integer userId = ((User) session.getAttribute("USER")).getUserid();
		logistics.setCollectgoodsaddr(shareaid11 + shareaid22 + shareaid33 + shareaid4);
		System.out.println("    rere         " + shareaid33);
		logistics.setUserid(userId);
		orderBiz.updatedLogistics(logistics);
		return "redirect:/c/lwx/getListLogistics";
	}
	
	/**
	 * 
	 * 
	 * ===============
	 * 
	 * 
	 */
	
	/**
	 * MVC：预约服务地址
	 * @param srVOobj
	 * @param model
	 * @return
	 */
	@PostMapping("serReserveUrl")
	public String serReserveUrl(SerReserveVO srVOobj,Model model) {
		Integer sid = srVOobj.getServiceID();	//服务编号
		ServiceDetailInfo serDetailObj = biz.queryServiceDetailInfo(sid);	//服务详情对象
		List<Resouroe> resouroeList = biz.queryResouroe();
		System.out.println(JSON.toJSONString(srVOobj));
		model.addAttribute("srVOobj",srVOobj);	
		model.addAttribute("serDetailObj",serDetailObj);
		model.addAttribute("resouroeList",resouroeList);
		return "fw-ydfw";
	}
	/**
	 * 评价查询
	 * @param num
	 * @param size
	 * @param sid
	 * @return
	 */
	@GetMapping("api/queryEvaluationserviceVO")
	@ResponseBody
	public PageInfo queryEvaluationserviceVO(Integer num,Integer size,Integer sid) {
		//评价查询
		return biz.queryEvaluationserviceVO(num,size,sid);
	}
	/**
	 * 举报商家
	 * @param obj
	 * @return
	 */
	@PostMapping("api/report")
	@ResponseBody
	public Map<String,String> saveServiceReport(HttpSession session,Integer businessID,Integer serviceID,Integer ctID) {
		Map<String,String> message = new HashMap<String,String>();
		User loginUser = (User)session.getAttribute("USER");	//登录对象：举报人
		Integer loginUserID = loginUser.getUserid();	//当前举报人用户编号
		if(biz.saveServiceReport(businessID, serviceID, loginUserID, ctID)>0) {
			message.put("code", "200");
			message.put("msg", "举报完成，请等待管理员审核");
		}else {
			message.put("code", "500");
			message.put("msg", "由于未知原因，举报失败！");
		}
		return message;
	}
	
	/**
	 * 点击服务详情跳转对应详情MVC地址
	 * @param htmlUrl
	 * @param sid
	 * @param uid
	 * @return
	 */
	@GetMapping("serviceDetailUrl")
	public String serviceDetailUrl(Model model,String htmlUrl,Integer sid,Integer uid) {
		//查询发布服务的商家信息
		ServiceMerchantInfo serMerchantObj = biz.queryServiceMerchantInfo(uid,sid);
		//查询服务信息
		ServiceDetailInfo serDetailObj = biz.queryServiceDetailInfo(sid);
		serDetailObj.setSerDesList(biz.queryServiceDes(sid));
		//服务评价星级人数查询
		EsLevelVO esLObj = biz.queryEsLevelVO(sid);
		//同城服务查询
		List<SameServiceVO> sameserList = biz.querySameServiceVO(sid);
		//举报原因查询
		List<Complainttype> complainttypeList = biz.queryComplainttype();
		//更新浏览数
		biz.updateServiceBrowseNumber(sid);
		//广告查询：未完成
		model.addAttribute("serMerchantObj",serMerchantObj);
		model.addAttribute("serDetailObj",serDetailObj);
		model.addAttribute("esLObj",esLObj);
		model.addAttribute("sameserList",sameserList);
		model.addAttribute("complainttypeList",complainttypeList);
		return htmlUrl;
	}
	
	/**
	 * 点击服务跳转对应MVC地址
	 * @param htmlUrl 跳转的网页名称
	 * @param stid	服务类别编号
	 * @return
	 */
	@GetMapping("serviceUrl")
	public String serviceUrl(String htmlUrl,Integer stid,Model model) {
		//查询国家
		List<Sharea> countryList = biz.querySharea(null, false);	
		//根据一级服务类别获取子类别
		List<Servicetype> serTypeList = biz.queryServiceType(stid, 1);
		//根据一级服务类别获取级别
		List<Servicelevel> serLevelList = biz.queryServicelevel(stid);
		model.addAttribute("countryList",countryList);	//将国家存入request
		model.addAttribute("serTypeList",serTypeList);	//将当前一级服务类别的子类别存入request
		model.addAttribute("serLevelList",serLevelList);//将当前一级服务类别的级别存入request
		return htmlUrl;
	}
	
	/**
	 * MVC:提交预定
	 * @return
	 */
	@PostMapping("serReserve")
	public String submitReserve(HttpSession session,SerReserveVO obj,MultipartFile hyFile) {
		User loginUser = (User)session.getAttribute("USER");
		Integer loginUserID = loginUser.getUserid();	//当前登录用户编号
		//时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//订单号
		String orderID = sdf.format(new Date());
		Integer rd =  (int)(Math.random()*899+100);
		orderID = orderID+rd;	//三位随机数添加到订单号后面
		if(obj.getServiceTypeID()==4) {
			try {
				String fileName = Upload.uploadFile(hyFile);
				obj.setUploadPath(fileName);
			} catch (IllegalStateException | IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		obj.setOrderID(orderID);
		obj.setUserID(loginUserID);
		biz.submitReserve(obj);
		return "redirect:/c/lwx/order/query/list";
	}
	
	/**
	 * MVC:首页地址
	 * @param model
	 * @return
	 */
	@GetMapping("homeUrl")
	public String homeUrl(Model model) {
		//首页社区服务轮播图广告位查询
		List<AdvertisementVO> homeSlideshowList = biz.queryHomeAdvertising(1);
		//首页社区服务中间广告位查询
		List<AdvertisementVO> homeMidAdvertingList = biz.queryHomeAdvertising(2);
		//首页社区上方左右广告位查询
		List<AdvertisementVO> homeTopAdvertingList = biz.queryHomeAdvertising(3);
		//首页社区下方广告位查询
		List<AdvertisementVO> homeBottomAdvertingList = biz.queryHomeAdvertising(4);
		//五大星级服务商家查询
		List<SerRecommendVO> recommendStidByOneList = biz.querySerRecommendVO(1);	//自驾游
		List<SerRecommendVO> recommendStidByTwoList = biz.querySerRecommendVO(2);	//微整形
		List<SerRecommendVO> recommendStidByThreeList = biz.querySerRecommendVO(3);	//留学中介
		List<SerRecommendVO> recommendStidByFourList = biz.querySerRecommendVO(4);	//韩语翻译
		List<SerRecommendVO> recommendStidByFiveList = biz.querySerRecommendVO(5);	//学习资源
		//韩汀社区论坛热门贴查询
		List<HomePostVO> homePostVOList = biz.queryHomePostVO();	//最新帖子
		List<Post> homePostMakeUpList = biz.queryHomePostByMakeup();	//美妆版块帖子
		model.addAttribute("homeSlideshowList",homeSlideshowList);
		model.addAttribute("homeMidAdvertingList",homeMidAdvertingList);
		model.addAttribute("recommendStidByOneList",recommendStidByOneList);
		model.addAttribute("recommendStidByTwoList",recommendStidByTwoList);
		model.addAttribute("recommendStidByThreeList",recommendStidByThreeList);
		model.addAttribute("recommendStidByFourList",recommendStidByFourList);
		model.addAttribute("recommendStidByFiveList",recommendStidByFiveList);
		model.addAttribute("homePostVOList",homePostVOList);
		model.addAttribute("homePostMakeUpList",homePostMakeUpList);
		model.addAttribute("homeTopAdvertingList",homeTopAdvertingList);
		model.addAttribute("homeBottomAdvertingList",homeBottomAdvertingList);
		return "fw-sy";
	}
	
	
	/**
	 * 收藏服务
	 * @param session
	 * @param sid
	 * @return
	 */
	@GetMapping("api/serviceCollection")
	@ResponseBody
	public Map<String,String> serviceCollection(HttpSession session,Integer sid){
		Map<String,String> message = new HashMap<String,String>();
		User loginUser = (User)session.getAttribute("USER");
		Integer uid = loginUser.getUserid();
		if(biz.queryUserSerCollectionCheck(uid, sid)==null) {
			biz.saveSerCollection(uid, sid);
			message.put("code", "200");
			message.put("msg", "已收藏");
		}else {
			biz.deleteSerCollection(uid, sid);
			message.put("code", "200");
			message.put("msg", "取消收藏");
		}
		return message;
	}
	
	/**
	 * 
	 * @title:queryService
	 * @Description:TODO我发布的服务
	 * @param session
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("getServices")
	public String  queryService(HttpSession session,Model model,Integer pageNum,Integer pageSize) {
		Integer userID=((User)session.getAttribute("USER")).getUserid();
		PageInfo<ServicesVo> pageInfo=biz.queryServices(pageNum, pageSize,userID);
		model.addAttribute("PAGE_INFO", pageInfo);
		return "sjzx-services";
	}
}
