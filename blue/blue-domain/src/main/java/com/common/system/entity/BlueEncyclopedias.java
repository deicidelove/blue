package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BlueEncyclopedias implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8362033086068207447L;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_advert.sid
	 * 
	 * @mbggenerated
	 */
	private Integer sid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_advert.type
	 * 
	 * @mbggenerated
	 */
	private Integer type;

	private String title;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_advert.url
	 * 
	 * @mbggenerated
	 */
	private String url;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_advert.is_delete
	 * 
	 * @mbggenerated
	 */
	private Short isDelete;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_advert.update_time
	 * 
	 * @mbggenerated
	 */
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_advert.create_time
	 * 
	 * @mbggenerated
	 */
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column tb_blue_advert.context
	 * 
	 * @mbggenerated
	 */
	private String context;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_advert.sid
	 * 
	 * @return the value of tb_blue_advert.sid
	 * 
	 * @mbggenerated
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_advert.sid
	 * 
	 * @param sid
	 *            the value for tb_blue_advert.sid
	 * 
	 * @mbggenerated
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_advert.type
	 * 
	 * @return the value of tb_blue_advert.type
	 * 
	 * @mbggenerated
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_advert.type
	 * 
	 * @param type
	 *            the value for tb_blue_advert.type
	 * 
	 * @mbggenerated
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_advert.url
	 * 
	 * @return the value of tb_blue_advert.url
	 * 
	 * @mbggenerated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_advert.url
	 * 
	 * @param url
	 *            the value for tb_blue_advert.url
	 * 
	 * @mbggenerated
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_advert.is_delete
	 * 
	 * @return the value of tb_blue_advert.is_delete
	 * 
	 * @mbggenerated
	 */
	public Short getIsDelete() {
		return isDelete;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_advert.is_delete
	 * 
	 * @param isDelete
	 *            the value for tb_blue_advert.is_delete
	 * 
	 * @mbggenerated
	 */
	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_advert.update_time
	 * 
	 * @return the value of tb_blue_advert.update_time
	 * 
	 * @mbggenerated
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_advert.update_time
	 * 
	 * @param updateTime
	 *            the value for tb_blue_advert.update_time
	 * 
	 * @mbggenerated
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_advert.create_time
	 * 
	 * @return the value of tb_blue_advert.create_time
	 * 
	 * @mbggenerated
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_advert.create_time
	 * 
	 * @param createTime
	 *            the value for tb_blue_advert.create_time
	 * 
	 * @mbggenerated
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column tb_blue_advert.context
	 * 
	 * @return the value of tb_blue_advert.context
	 * 
	 * @mbggenerated
	 */
	public String getContext() {
		return context;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column tb_blue_advert.context
	 * 
	 * @param context
	 *            the value for tb_blue_advert.context
	 * 
	 * @mbggenerated
	 */
	public void setContext(String context) {
		this.context = context == null ? null : context.trim();
	}
}