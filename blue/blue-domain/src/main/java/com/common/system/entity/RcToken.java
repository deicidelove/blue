package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

public class RcToken implements Serializable {
	private static final long serialVersionUID = 6861310738784110334L;
	
	private Integer id;
	private String appId;
	private String token;
	private Date expireTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
}