package com.common.system.weixin.dto;

import java.io.Serializable;

public class CreateTokenActionScene implements Serializable {
	private static final long serialVersionUID = 6960345820109242468L;

	/**
	 * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 */
	private Integer scene_id;
	
	/**
	 * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64  
	 */
	private String scene_str;

	public Integer getScene_id() {
		return scene_id;
	}

	public void setScene_id(Integer scene_id) {
		this.scene_id = scene_id;
	}

	public String getScene_str() {
		return scene_str;
	}

	public void setScene_str(String scene_str) {
		this.scene_str = scene_str;
	}
	
}