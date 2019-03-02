package com.accp.vo.lwx.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 预约服务显示和提交
 * @author lsm
 *
 */
public class SerReserveVO {
	private String orderID;	//订单号
	private Integer userID;	//用户编号
	private Integer serviceID;	//服务编号 
	private Integer serviceTypeID;	//一级服务类别编号
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date reserveStartDate;	//预约开始时间：自驾游
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date reserveEndDate;	//预约结束时间：自驾游
	private Integer reserveDayCount;	//预约天数：自驾游	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date reserveDate;	//预约日期：微整形
	private Integer personCount;	//人数：自驾游、微整形、留学中介
	private Integer quantity;	//数量：韩语翻译
	private float subtotal;	//小计
	private Integer resouroeID;	//资料类别编号
	private String remarks;	//备注留言
	private String uploadPath;	//文件上传路径
	public Integer getServiceID() {
		return serviceID;
	}
	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}
	public Date getReserveStartDate() {
		return reserveStartDate;
	}
	public void setReserveStartDate(Date reserveStartDate) {
		this.reserveStartDate = reserveStartDate;
	}
	public Date getReserveEndDate() {
		return reserveEndDate;
	}
	public void setReserveEndDate(Date reserveEndDate) {
		this.reserveEndDate = reserveEndDate;
	}
	public Integer getReserveDayCount() {
		return reserveDayCount;
	}
	public void setReserveDayCount(Integer reserveDayCount) {
		this.reserveDayCount = reserveDayCount;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public Integer getPersonCount() {
		return personCount;
	}
	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	public Integer getResouroeID() {
		return resouroeID;
	}
	public void setResouroeID(Integer resouroeID) {
		this.resouroeID = resouroeID;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getServiceTypeID() {
		return serviceTypeID;
	}
	public void setServiceTypeID(Integer serviceTypeID) {
		this.serviceTypeID = serviceTypeID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
}
