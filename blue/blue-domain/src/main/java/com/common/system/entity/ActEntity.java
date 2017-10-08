package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5198787619882175851L;
	
	private Integer actId;
	
	private String actName;
	
	private Integer actTotalNum;
	
	private Integer actGivingNum;
	
	private Integer actPeriods;
	
	private Integer actIsExpire = 0;
	
	private Integer actIsDelete = 0;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public Integer getActTotalNum() {
		return actTotalNum;
	}

	public void setActTotalNum(Integer actTotalNum) {
		this.actTotalNum = actTotalNum;
	}

	public Integer getActGivingNum() {
		return actGivingNum;
	}

	public void setActGivingNum(Integer actGivingNum) {
		this.actGivingNum = actGivingNum;
	}

	public Integer getActPeriods() {
		return actPeriods;
	}

	public void setActPeriods(Integer actPeriods) {
		this.actPeriods = actPeriods;
	}

	public Integer getActIsExpire() {
		return actIsExpire;
	}

	public void setActIsExpire(Integer actIsExpire) {
		this.actIsExpire = actIsExpire;
	}

	public Integer getActIsDelete() {
		return actIsDelete;
	}

	public void setActIsDelete(Integer actIsDelete) {
		this.actIsDelete = actIsDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
