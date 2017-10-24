package com.common.system.dto;

import java.io.Serializable;

public class MessageSendResult implements Serializable {
	private static final long serialVersionUID = 4112264083104630837L;

	private String status;
	
	private String send_id;
	
	private Integer fee;
	
	private Integer sms_credits;
	
	private String code;
	
	private String msg;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSend_id() {
		return send_id;
	}

	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getSms_credits() {
		return sms_credits;
	}

	public void setSms_credits(Integer sms_credits) {
		this.sms_credits = sms_credits;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
