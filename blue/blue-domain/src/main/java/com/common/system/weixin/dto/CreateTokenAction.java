package com.common.system.weixin.dto;

import java.io.Serializable;

public class CreateTokenAction implements Serializable {
	private static final long serialVersionUID = -2608481057778591354L;

	/**
	 * 二维码类型，QR_SCENE为临时的整型参数值，
	 * QR_STR_SCENE为临时的字符串参数值，
	 * QR_LIMIT_SCENE为永久的整型参数值，
	 * QR_LIMIT_STR_SCENE为永久的字符串参数值
	 */
	private String action_name;
	
	/**
	 * 二维码详细信息
	 */
	private CreateTokenActionInfo action_info;

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public CreateTokenActionInfo getAction_info() {
		return action_info;
	}

	public void setAction_info(CreateTokenActionInfo action_info) {
		this.action_info = action_info;
	}
	
}