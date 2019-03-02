package com.accp.biz.lwx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lwx.EvaluateDao;
import com.accp.dao.lwx.GoldnotesDao;
import com.accp.dao.lwx.OrderDao;
import com.accp.dao.lwx.RefundDao;
import com.accp.dao.lwx.UserDao;
import com.accp.pojo.Banktype;
import com.accp.pojo.Evaluationservice;
import com.accp.pojo.Goldnotes;
import com.accp.pojo.Integralrecord;
import com.accp.pojo.Logistics;
import com.accp.pojo.News;
import com.accp.pojo.Orders;
import com.accp.pojo.Putforward;
import com.accp.pojo.Services;
import com.accp.pojo.Servicetype;
import com.accp.pojo.Sharea;
import com.accp.pojo.User;
import com.accp.vo.lwx.OrderInfo;
import com.accp.vo.lwx.OrderInfoVo;
import com.accp.vo.lwx.OrdersVo;
import com.accp.vo.lwx.RefundVo;
import com.accp.vo.lwx.Evaluate.EvaluateVo;
import com.accp.vo.lwx.Evaluate.EvaluationserviceToservicesVo;
import com.accp.vo.lwx.myfavorite.UserToServicesVo;
import com.accp.vo.lwx.myfavorite.UserVo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class OrderBiz {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EvaluateDao evaluateDao;
	@Autowired
	private RefundDao refundDao;
	@Autowired
	private GoldnotesDao goldnotesDao;
	
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
	
	/**
	 * 支付订单
	 * 
	 * @param usermoney
	 *            相对用户余额
	 * @param userid
	 *            用户编号
	 * @param order
	 *            订单
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean payOrder(double usermoney, int userid, OrdersVo order) {
//		usermoney小计
		User user = userDao.queryUserById(userid);
		if (user.getUsermoney() < usermoney) {
			return false;
		}
		userDao.saveXtxx(orderDao.queryOrderById(order.getOrderid()).getService().getUser().getUserid(),
				"用户已支付订单：" + order.getOrderid());
		Goldnotes goldnotes = new Goldnotes();
		goldnotes.setUserid(user.getUserid());
		goldnotes.setAcquisitionmode(1);
		goldnotes.setRecorddate(new Date());
		goldnotes.setRecorddescribe("支付订单：" + order.getOrderid());
		goldnotes.setRecordinandout((float) -usermoney);
		goldnotes.setAuditstatus(2);
		goldnotesDao.addGoldnotes(goldnotes);
		userDao.updateUserMoney((float) -usermoney, userid);
		return orderDao.updateOrder(order);
	}

	/**
	 * 取消订单
	 * 
	 * @param order
	 *            订单
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean cancelOrder(OrdersVo order) {
		userDao.saveXtxx(orderDao.queryOrderById(order.getOrderid()).getService().getUser().getUserid(),
				"用户已取消订单：" + order.getOrderid());
		return orderDao.updateOrder(order);
	}

	/**
	 * 完成订单
	 * 
	 * @param usermoney
	 *            金额
	 * @param userid
	 *            商户编号
	 * @param order
	 *            订单
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean ok(double usermoney, int userid, OrdersVo order) {
		User user = orderDao.queryOrderById(order.getOrderid()).getService().getUser();
		userDao.saveXtxx(user.getUserid(), "用户已确认完成订单：" + order.getOrderid());
		Goldnotes goldnotes = new Goldnotes();
		goldnotes.setUserid(user.getUserid());
		goldnotes.setAcquisitionmode(2);
		goldnotes.setRecorddate(new Date());
		goldnotes.setRecorddescribe("订单收益：" + order.getOrderid());
		goldnotes.setRecordinandout((float) (usermoney * 0.9));
		goldnotes.setAuditstatus(2);
		goldnotesDao.addGoldnotes(goldnotes);
		userDao.updateUserMoney((float) (usermoney * 0.9), userid);
		return orderDao.updateOrder(order);
	}

	/**
	 * 评价订单
	 * 
	 * @param evaluate
	 *            评价
	 * @param order
	 *            订单
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean evaluateOk(EvaluateVo evaluate, OrdersVo order) {
		userDao.saveXtxx(orderDao.queryOrderById(order.getOrderid()).getService().getUser().getUserid(),
				"用户已评价订单：" + order.getOrderid());
		evaluateDao.saveEvaluate(evaluate);
		return orderDao.updateOrder(order);
	}

	/**
	 * 申请退款
	 * 
	 * @param refund
	 *            退款
	 * @param order
	 *            订单
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean refundok(RefundVo refund, OrdersVo order) {
		userDao.saveXtxx(orderDao.queryOrderById(order.getOrderid()).getService().getUser().getUserid(),
				"用户申请退款，订单：" + order.getOrderid());
		refundDao.saveRefund(refund);
		return orderDao.updateOrder(order);
	}
	
	
	/**
	 * 
	 * 
	 * ===================
	 * 
	 * 
	 * 
	 * 
	 */
	/**
	 * 查询当前登陆用户预定信息
	 * @return
	 */
	public  PageInfo<OrderInfoVo>  queryUserOrder(Integer userID,Integer orderStatus,Integer refundstatus,String orderID,Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(orderDao.queryUserOrder(userID, orderStatus, refundstatus,orderID));
	}
	/**
	 * 查询服务类型
	 * @param stid
	 * @return
	 */
	public Servicetype querySerType(Integer stid) {
		return orderDao.querySerType(stid);
	}
	/**
	 * 修改订单
	 * @param orderStatus
	 * @param orderID
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public int updateOrders(Integer orderStatus,String orderID) {
		return orderDao.updateOrders(orderStatus, orderID);
	}
	/**
	 * 查询所有订单
	 * @param userID
	 * @return
	 */
	public List<Orders> queryCountOrder(Integer userID) {
		return orderDao.queryCountOrder(userID);
	}
	/**
	 * 查询单个订单详情
	 * @param orderID 订单ID
	 * @return
	 */
	public OrderInfoVo queryAOrder(String orderID) {
		return orderDao.queryAOrder(orderID);
	}
	
	/**
	 * 金币流向表
	 * @param pageNum  
	 * @param pageSize
	 * @param userId用户id
	 * @return
	 */
    public  PageInfo<Goldnotes> goldnotesQueryAll(Integer pageNum,Integer pageSize,Integer userId,Integer acquisitionMode) {
    	 PageHelper.startPage(pageNum,pageSize);
  	   return new PageInfo<Goldnotes>(goldnotesDao.goldnotesQueryAll(userId,acquisitionMode));
    }
    /**
     * 查询积分流向
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    public PageInfo<Integralrecord>IntegralRecordQueryAll(Integer pageNum,Integer pageSize,Integer userId){
   	    PageHelper.startPage(pageNum,pageSize);
    	return new PageInfo<Integralrecord>(goldnotesDao.IntegralRecordQueryAll(userId));
    }
    /**
     * 添加金币提现记录表
     * @param Goldnotes 
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void addPutforWard(Putforward  putforward) {
    	User user=goldnotesDao.getUser(putforward.getUserid());
    	Float money=user.getUsermoney()-putforward.getMoney();
    	goldnotesDao.updUser(money, null,putforward.getUserid());
    	goldnotesDao.addPutforWard(putforward);
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		
		News news=new News();
    	news.setAddressee(putforward.getUserid());
    	news.setContent("您于"+dateString+"进行金币提现");
    	news.setMessagegroup(2);
    	news.setNewstype(1);
    	news.setReadstate(false);
    	news.setSendingtime(new Date());
    	news.setThesender(putforward.getUserid());
    	goldnotesDao.addNews(news);
	}
    /**
     * 添加金币充值记录表
     * @param goldnotes
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void addGoldnotes(Goldnotes  goldnotes) {
    	Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
    	News news=new News();
    	news.setAddressee(goldnotes.getUserid());
    	news.setContent("您于"+dateString+"进行了充值");
    	news.setMessagegroup(1);
    	news.setNewstype(1);
    	news.setReadstate(false);
    	news.setSendingtime(new Date());
    	news.setThesender(null);
    	goldnotesDao.addNews(news);
    	goldnotesDao.addGoldnotes(goldnotes);
	}
    /**
     * 查询物流记录
     * @param pageNum
     * @param pageSize
     * @param logistics
     * @return
     */
    public PageInfo<Logistics>getListLogistics(Integer pageNum,Integer pageSize,Logistics logistics){
    	PageHelper.startPage(pageNum, pageSize);
    	return new PageInfo<Logistics>(goldnotesDao.getListlogistics(logistics));
    }
    /**
     * 查询商品服务评价记录
     * @param pageNum
     * @param pageSize
     * @param evaluationservice
     * @return
     */
  public PageInfo<EvaluationserviceToservicesVo>getListEvaluationService(Integer pageNum,Integer pageSize,Evaluationservice evaluationservice){
	  PageHelper.startPage(pageNum, pageSize);
	  return new PageInfo<EvaluationserviceToservicesVo>(goldnotesDao.getListEvaluationService(evaluationservice));
  }
  /**
   * 查询银行类别
   * @return
   */
  public List<Banktype>getListBankType(){
	  return goldnotesDao.getListBankType();
  }
  /**
   * 添加物流 
   * @param goldnotes
   */
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
 public void addLogistics(Logistics logistics) {
	  goldnotesDao.addLogistics(logistics);
 }
  /**
   * 查询物流详情
   * @param id
   * @return
   */
  public Logistics getLogistics(Integer userId,Integer id) {
	  return goldnotesDao.getLogistics(userId, id);
  }
  public List<Sharea>getShAreaById(Integer id) {
	  return goldnotesDao.getShAreaById(id);
  }
  /**
   * 查询用户余额
   * @param userId
   * @return
   */
  public User getUser(Integer userId) {
	  return goldnotesDao.getUser(userId);
  }
  /**
   * 修改金币记录状态
   * @param recordId
   * @param auditStatus
   */
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
  public void updGoldnotes(Integer recordId,Integer auditStatus) {
	  goldnotesDao.updGoldnotes(recordId, auditStatus);
  }
  /**
   * 查询当前金币记录是否存在
   * @param recordId
   * @return
   */
  public Goldnotes getGoldnotesById(Integer recordId) {
	  return goldnotesDao.getGoldnotesById(recordId);
  }
  /**
   * 查询收藏的服务
   * @param pageNum
   * @param pageSize
   * @param userId
   * @return
   */
  public PageInfo<Services>getServicesByUserId(Integer pageNum,Integer pageSize,Integer userId){
	  PageHelper.startPage(pageNum, pageSize);
	  return new PageInfo<Services>(goldnotesDao.getServicesByUserId(userId));
  }
  /**
   * 查询收藏的服务及商家
   * @param pageNum
   * @param pageSize
   * @param userId
   * @return
   */
  public PageInfo<UserVo>getMerchantCollectionById(Integer pageNum,Integer pageSize,Integer userId){
	  PageHelper.startPage(pageNum, pageSize);
	  return new PageInfo<UserVo>(goldnotesDao.getMerchantCollectionById(userId));
  }
  /**
   * 修改用户余额合物流状态
   * @param moery
   * @param userId
   * @param logisticsid
   */
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
  public void updUser(Float moery,Integer userId,Integer logisticsid) {
	  Logistics logistics=new Logistics();
	  logistics.setAuditstatus(2);
	  logistics.setOrdertime(new Date());
	  logistics.setLogisticsid(logisticsid);
	    Logistics logisticss = goldnotesDao.getLogistics(userId,logisticsid);
	    Goldnotes goldnotes=new Goldnotes();
		goldnotes.setUserid(userId);
		goldnotes.setAcquisitionmode(2);
		goldnotes.setRecorddate(new Date());
		goldnotes.setRecorddescribe("物流支付订单号"+logisticss.getLogisticsid());
		goldnotes.setRecordinandout(-+(float)logisticss.getPrice());
		goldnotes.setAuditstatus(2);
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
    
		News news=new News();
    	news.setAddressee(goldnotes.getUserid());
    	news.setContent("您于"+dateString+"进行了物流支付");
    	news.setMessagegroup(1);
    	news.setNewstype(1);
    	news.setReadstate(false);
    	news.setSendingtime(new Date());
    	news.setThesender(null);
    	goldnotesDao.addNews(news);
		
    	goldnotesDao.addGoldnotes(goldnotes);
    	goldnotesDao.updatedLogistics(logistics);
    	goldnotesDao.updUser(moery,null,userId);
  }
  /**
   * 修改物流状态
   * @param logistics
   */
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
  public void updatedLogistics(Logistics logistics) {
	  goldnotesDao.updatedLogistics(logistics);
  }
  public List<UserToServicesVo>getUserToServicesVo(){
	  return goldnotesDao.getUserToServicesVo();
  }
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
  public void updateUserToGoldnotes(Float total_amount,String out_trade_no,Integer userId,Integer getAuditstatus) {
	  User user=goldnotesDao.getUser(userId);
  	if(user.getUsermoney()==null) {
  		user.setUsermoney((float) 0);
  	}
  	Float money=user.getUsermoney()+total_amount;
  	  Integer userIntegra2=(int) (total_amount/10);
	  user.setUserintegral(user.getUserintegral()+userIntegra2);
  	 // user.setuserIntegra1();
	  Integralrecord Integralrecord=new Integralrecord();
	  Integralrecord.setUserid(userId);
	  Integralrecord.setIrdate(new Date());
	  Integralrecord.setIrdescribe("充值送积分");
	  Integralrecord.setAuditstatus(4);
	  Integralrecord.setRecordinandout(user.getUserintegral());
	  goldnotesDao.addIntegralRecord(Integralrecord);
	  goldnotesDao.updGoldnotes(Integer.parseInt(out_trade_no),getAuditstatus);
	  goldnotesDao.updUser(money, user.getUserintegral(),userId);
  }
}
