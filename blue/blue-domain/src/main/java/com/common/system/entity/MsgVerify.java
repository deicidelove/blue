package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

public class MsgVerify implements Serializable {
	private static final long serialVersionUID = -5986453744780430881L;
	private Integer id;
	// 微信OpenId
	private String weixinOpenId;
	// 过期时间
	private Date expireTime;
	// 验证码
	private String checkCode;
	
	public MsgVerify(){}
	
	public MsgVerify(String weixinOpenId, String checkCode, Date expireTime) {
		this.weixinOpenId = weixinOpenId;
		this.expireTime = expireTime;
		this.checkCode = checkCode;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getWeixinOpenId() {
		return weixinOpenId;
	}
	public void setWeixinOpenId(String weixinOpenId) {
		this.weixinOpenId = weixinOpenId;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
}
