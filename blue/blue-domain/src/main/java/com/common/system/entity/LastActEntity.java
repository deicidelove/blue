package com.common.system.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LastActEntity {

	private Integer sid;
	
	private String lastActName;
	
	private String lastActTitle;
	
	private String lastActListImg;
	
	private String lastActContent;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	private Boolean isDelete;

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
	 * @return the lastActName
	 */
	public String getLastActName() {
		return lastActName;
	}

	/**
	 * @param lastActName the lastActName to set
	 */
	public void setLastActName(String lastActName) {
		this.lastActName = lastActName;
	}

	/**
	 * @return the lastActTitle
	 */
	public String getLastActTitle() {
		return lastActTitle;
	}

	/**
	 * @param lastActTitle the lastActTitle to set
	 */
	public void setLastActTitle(String lastActTitle) {
		this.lastActTitle = lastActTitle;
	}

	/**
	 * @return the lastActListImg
	 */
	public String getLastActListImg() {
		return lastActListImg;
	}

	/**
	 * @param lastActListImg the lastActListImg to set
	 */
	public void setLastActListImg(String lastActListImg) {
		this.lastActListImg = lastActListImg;
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
	 * @return the isDelete
	 */
	public Boolean getIsDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the lastActContent
	 */
	public String getLastActContent() {
		return lastActContent;
	}

	/**
	 * @param lastActContent the lastActContent to set
	 */
	public void setLastActContent(String lastActContent) {
		this.lastActContent = lastActContent;
	}
}
