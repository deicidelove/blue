package com.common.system.weixin.dto;

import java.io.Serializable;

/** 申请Token的返回结果 **/
public class ApplyTokenResult implements Serializable {
	private static final long serialVersionUID = -407206597181299488L;
	
	// 获取到的凭证
	private String access_token;
	// 凭证有效时间，单位：秒
	private Integer expires_in;
	// 错误码
	private Integer errcode;
	// 错误信息
	private String errmsg;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}