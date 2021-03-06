package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BlueOppointment implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3578505793972595535L;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_oppointment.sid
	 * 
	 * @mbggenerated
	 */
	private Integer sid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_oppointment.staff_id
	 * 
	 * @mbggenerated
	 */
	private Integer staffId;
	
	private String staffName = "";
	
	private Integer deptId;
	
	private String deptName="";

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_oppointment.pation_id
	 * 
	 * @mbggenerated
	 */
	private Integer pationId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_oppointment.user_id
	 * 
	 * @mbggenerated
	 */
	private String userId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_oppointment.pay_money
	 * 
	 * @mbggenerated
	 */
	private Float payMoney;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_oppointment.order_time
	 * 
	 * @mbggenerated
	 */
	private String orderTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_oppointment.create_time
	 * 
	 * @mbggenerated
	 */
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_oppointment.context
	 * 
	 * @mbggenerated
	 */
	private String context;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_oppointment.sid
	 * 
	 * @return the value of tb_blue_oppointment.sid
	 * 
	 * @mbggenerated
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_oppointment.sid
	 * 
	 * @param sid
	 *            the value for tb_blue_oppointment.sid
	 * 
	 * @mbggenerated
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_oppointment.staff_id
	 * 
	 * @return the value of tb_blue_oppointment.staff_id
	 * 
	 * @mbggenerated
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_oppointment.staff_id
	 * 
	 * @param staffId
	 *            the value for tb_blue_oppointment.staff_id
	 * 
	 * @mbggenerated
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_oppointment.pation_id
	 * 
	 * @return the value of tb_blue_oppointment.pation_id
	 * 
	 * @mbggenerated
	 */
	public Integer getPationId() {
		return pationId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_oppointment.pation_id
	 * 
	 * @param pationId
	 *            the value for tb_blue_oppointment.pation_id
	 * 
	 * @mbggenerated
	 */
	public void setPationId(Integer pationId) {
		this.pationId = pationId;
	}

	
	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_oppointment.pay_money
	 * 
	 * @return the value of tb_blue_oppointment.pay_money
	 * 
	 * @mbggenerated
	 */
	public Float getPayMoney() {
		return payMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_oppointment.pay_money
	 * 
	 * @param payMoney
	 *            the value for tb_blue_oppointment.pay_money
	 * 
	 * @mbggenerated
	 */
	public void setPayMoney(Float payMoney) {
		this.payMoney = payMoney;
	}

	

	/**
	 * @return the orderTime
	 */
	public String getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_oppointment.create_time
	 * 
	 * @return the value of tb_blue_oppointment.create_time
	 * 
	 * @mbggenerated
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_oppointment.create_time
	 * 
	 * @param createTime
	 *            the value for tb_blue_oppointment.create_time
	 * 
	 * @mbggenerated
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_oppointment.context
	 * 
	 * @return the value of tb_blue_oppointment.context
	 * 
	 * @mbggenerated
	 */
	public String getContext() {
		return context;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_oppointment.context
	 * 
	 * @param context
	 *            the value for tb_blue_oppointment.context
	 * 
	 * @mbggenerated
	 */
	public void setContext(String context) {
		this.context = context == null ? null : context.trim();
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the deptId
	 */
	public Integer getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}