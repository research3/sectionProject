package com.accp.dao.lwx;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.vo.lwx.RefundVo;


public interface RefundDao {
	/**
	 * 新增退款
	 * 
	 * @param refund
	 *            退款
	 * @return
	 */
	boolean saveRefund(@Param("refund") RefundVo refund);
	
	/**
	 * 获取退款详单
	 * 
	 * @param orderId
	 *            订单编号
	 * @return
	 */
	RefundVo queryRefundByOrderId(@Param("orderid") String orderId);
	
	/**
	 * 修改退款
	 * 
	 * @param refund
	 *            退款
	 * @return
	 */
	boolean updateRefund(@Param("refund") RefundVo refund);

	/**
	 * 查询收到的退款列表
	 * 
	 * @param userid
	 *            用户编号
	 * @return
	 */
	List<RefundVo> queryRefundList(@Param("userid") Integer userid);

	/**
	 * 查询我的退款列表
	 * 
	 * @param userid
	 * @return
	 */
	List<RefundVo> queryMyRefundList(@Param("userid") Integer userid);
}
