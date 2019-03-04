package com.accp.biz.lwx;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lwx.GoldnotesDao;
import com.accp.dao.lwx.OrderDao;
import com.accp.dao.lwx.RefundDao;
import com.accp.dao.lwx.UserDao;
import com.accp.pojo.Goldnotes;
import com.accp.vo.lwx.OrdersVo;
import com.accp.vo.lwx.RefundVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class RefundBiz {
	
	@Autowired
	private RefundDao refundDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private GoldnotesDao goldnotesDao;

	
	/**
	 * 获取退款详单
	 * 
	 * @param orderId
	 *            订单编号
	 * @return
	 */
	public RefundVo queryRefundByOrderId(String orderId) {
		return refundDao.queryRefundByOrderId(orderId);
	}
	
	/**
	 * 申请管理员介入
	 * 
	 * @param order
	 *            订单
	 * @param refund
	 *            退款
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean applyAdmin(OrdersVo order, RefundVo refund) {
		userDao.saveXtxx(orderDao.queryOrderById(order.getOrderid()).getService().getUser().getUserid(),
				"退款已申请管理员介入，订单号：" + order.getOrderid());
		orderDao.updateOrder(order);
		return refundDao.updateRefund(refund);
	}

	/**
	 * 查询收到的退款列表
	 * 
	 * @param userid
	 *            用户编号
	 * @param page
	 *            页
	 * @param size
	 *            行
	 * @return
	 */
	public PageInfo<RefundVo> queryRefundList(Integer userid, int page, int size) {
		PageHelper.startPage(page, size);
		return new PageInfo<RefundVo>(refundDao.queryRefundList(userid));
	}

	/**
	 * 同意退款
	 * 
	 * @param order
	 *            订单
	 * @param refund
	 *            退款
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean refundOk(OrdersVo orderInfo, OrdersVo order, RefundVo refund) {
		Integer buyer = orderInfo.getUserid();
		Integer seller = orderInfo.getService().getUser().getUserid();
		Float money = refundDao.queryRefundByOrderId(order.getOrderid()).getApplyrefundmoney();
		userDao.saveXtxx(buyer, "卖家同意退款，订单：" + order.getOrderid());
		Goldnotes goldnotes = new Goldnotes();
		goldnotes.setUserid(buyer);
		goldnotes.setAcquisitionmode(4);
		goldnotes.setRecorddate(new Date());
		goldnotes.setRecorddescribe("订单退款：" + order.getOrderid());
		goldnotes.setRecordinandout((float) money);
		goldnotes.setAuditstatus(2);
		goldnotesDao.addGoldnotes(goldnotes);
		Goldnotes goldnotes1 = new Goldnotes();
		goldnotes1.setUserid(seller);
		goldnotes1.setAcquisitionmode(4);
		goldnotes1.setRecorddate(new Date());
		goldnotes1.setRecorddescribe("订单退款：" + order.getOrderid());
		goldnotes1.setRecordinandout((float) (-money * 0.9));
		goldnotes1.setAuditstatus(2);
		goldnotesDao.addGoldnotes(goldnotes1);
		refund.setActualrefundmoney(money);
		userDao.updateUserMoney(money, buyer);
		userDao.updateUserMoney((float) (-money * 0.9), seller);
		orderDao.updateOrder(order);
		return refundDao.updateRefund(refund);
	}

	/**
	 * 拒绝退款
	 * 
	 * @param refund
	 *            退款
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean refundNo(OrdersVo order, RefundVo refund) {
		userDao.saveXtxx(orderDao.queryOrderById(order.getOrderid()).getUserid(), "卖家拒绝退款，订单：" + order.getOrderid());
		orderDao.updateOrder(order);
		return refundDao.updateRefund(refund);
	}

	/**
	 * 查询我的退款列表
	 * 
	 * @param userid
	 *            用户编号
	 * @param page
	 *            页
	 * @param size
	 *            行
	 * @return
	 */
	public PageInfo<RefundVo> queryMyRefundList(Integer userid, int page, int size) {
		PageHelper.startPage(page, size);
		return new PageInfo<RefundVo>(refundDao.queryMyRefundList(userid));
	}
}
