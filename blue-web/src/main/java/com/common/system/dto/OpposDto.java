/**
 * 
 */
package com.common.system.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author amkong
 *
 */
public class OpposDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7105197664979661204L;

	private Integer sid;

	private Integer staffId;
	
	private String head_url = "";
	
	private String staffName = "";
	
	private String positionName ="";
	
	private String introduce = "";
	
	private Integer deptId;
	
	private String deptName="";

	private Integer pationId;

	private String userId;

	private Float payMoney;

	private String orderTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	private String context;
	
	private float isPass;

	/**
	 * @return the sid
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the head_url
	 */
	public String getHead_url() {
		return head_url;
	}

	/**
	 * @param head_url the head_url to set
	 */
	public void setHead_url(String head_url) {
		this.head_url = head_url;
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
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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

	/**
	 * @return the pationId
	 */
	public Integer getPationId() {
		return pationId;
	}

	/**
	 * @param pationId the pationId to set
	 */
	public void setPationId(Integer pationId) {
		this.pationId = pationId;
	}

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
	 * @return the payMoney
	 */
	public Float getPayMoney() {
		return payMoney;
	}

	/**
	 * @param payMoney the payMoney to set
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
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * @return the isPass
	 */
	public float getIsPass() {
		return isPass;
	}

	/**
	 * @param isPass the isPass to set
	 */
	public void setIsPass(float isPass) {
		this.isPass = isPass;
	}

	
	
	
}
