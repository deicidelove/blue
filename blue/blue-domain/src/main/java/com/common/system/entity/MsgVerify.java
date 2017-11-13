package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

public class MsgVerify implements Serializable {
	private static final long serialVersionUID = -5986453744780430881L;
	private Integer id;
	// 电话号码
	private String phoneNumber;
	// 过期时间
	private Date expireTime;
	// 验证码
	private String checkCode;
	
	public MsgVerify(){}
	
	public MsgVerify(String phoneNumber, String checkCode, Date expireTime) {
		this.phoneNumber = phoneNumber;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
}