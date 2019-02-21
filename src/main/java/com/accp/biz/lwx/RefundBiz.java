package com.accp.biz.lwx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.lwx.RefundDao;
import com.accp.vo.lwx.RefundVo;


@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class RefundBiz {
	
	@Autowired
	private RefundDao refundDao;
	
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
}
