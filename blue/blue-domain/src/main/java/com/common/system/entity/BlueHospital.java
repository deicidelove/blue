package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BlueHospital implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7142333124081378455L;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_hospital.sid
	 * 
	 * @mbggenerated
	 */
	private Integer sid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_hospital.type
	 * 
	 * @mbggenerated
	 */
	private Integer type;
	
	private String url = "";

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_hospital.title
	 * 
	 * @mbggenerated
	 */
	private String title = "";

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_hospital.update_time
	 * 
	 * @mbggenerated
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_hospital.create_time
	 * 
	 * @mbggenerated
	 */
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_hospital.context
	 * 
	 * @mbggenerated
	 */
	private String context = "";

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_hospital.sid
	 * 
	 * @return the value of tb_blue_hospital.sid
	 * 
	 * @mbggenerated
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_hospital.sid
	 * 
	 * @param sid
	 *            the value for tb_blue_hospital.sid
	 * 
	 * @mbggenerated
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_hospital.type
	 * 
	 * @return the value of tb_blue_hospital.type
	 * 
	 * @mbggenerated
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_hospital.type
	 * 
	 * @param type
	 *            the value for tb_blue_hospital.type
	 * 
	 * @mbggenerated
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_hospital.title
	 * 
	 * @return the value of tb_blue_hospital.title
	 * 
	 * @mbggenerated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_hospital.title
	 * 
	 * @param title
	 *            the value for tb_blue_hospital.title
	 * 
	 * @mbggenerated
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_hospital.update_time
	 * 
	 * @return the value of tb_blue_hospital.update_time
	 * 
	 * @mbggenerated
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_hospital.update_time
	 * 
	 * @param updateTime
	 *            the value for tb_blue_hospital.update_time
	 * 
	 * @mbggenerated
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_hospital.create_time
	 * 
	 * @return the value of tb_blue_hospital.create_time
	 * 
	 * @mbggenerated
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_hospital.create_time
	 * 
	 * @param createTime
	 *            the value for tb_blue_hospital.create_time
	 * 
	 * @mbggenerated
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_hospital.context
	 * 
	 * @return the value of tb_blue_hospital.context
	 * 
	 * @mbggenerated
	 */
	public String getContext() {
		return context;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_hospital.context
	 * 
	 * @param context
	 *            the value for tb_blue_hospital.context
	 * 
	 * @mbggenerated
	 */
	public void setContext(String context) {
		this.context = context == null ? null : context.trim();
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}