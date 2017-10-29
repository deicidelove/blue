package com.common.system.entity;

import java.util.Date;

public class BluePation {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_pation.sid
	 * 
	 * @mbggenerated
	 */
	private Integer sid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_pation.name
	 * 
	 * @mbggenerated
	 */
	private String name;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_pation.phone
	 * 
	 * @mbggenerated
	 */
	private String phone;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_pation.user_id
	 * 
	 * @mbggenerated
	 */
	private String userId;
	
	private Integer isDefault;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_pation.update_time
	 * 
	 * @mbggenerated
	 */
	private Date updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_pation.create_time
	 * 
	 * @mbggenerated
	 */
	private Date createTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_pation.sid
	 * 
	 * @return the value of tb_blue_pation.sid
	 * 
	 * @mbggenerated
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_pation.sid
	 * 
	 * @param sid
	 *            the value for tb_blue_pation.sid
	 * 
	 * @mbggenerated
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_pation.name
	 * 
	 * @return the value of tb_blue_pation.name
	 * 
	 * @mbggenerated
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_pation.name
	 * 
	 * @param name
	 *            the value for tb_blue_pation.name
	 * 
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_pation.phone
	 * 
	 * @return the value of tb_blue_pation.phone
	 * 
	 * @mbggenerated
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_pation.phone
	 * 
	 * @param phone
	 *            the value for tb_blue_pation.phone
	 * 
	 * @mbggenerated
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_pation.user_id
	 * 
	 * @return the value of tb_blue_pation.user_id
	 * 
	 * @mbggenerated
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_pation.user_id
	 * 
	 * @param userId
	 *            the value for tb_blue_pation.user_id
	 * 
	 * @mbggenerated
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_pation.update_time
	 * 
	 * @return the value of tb_blue_pation.update_time
	 * 
	 * @mbggenerated
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_pation.update_time
	 * 
	 * @param updateTime
	 *            the value for tb_blue_pation.update_time
	 * 
	 * @mbggenerated
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_pation.create_time
	 * 
	 * @return the value of tb_blue_pation.create_time
	 * 
	 * @mbggenerated
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_pation.create_time
	 * 
	 * @param createTime
	 *            the value for tb_blue_pation.create_time
	 * 
	 * @mbggenerated
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the isDefault
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
}