package com.common.system.dto;

import java.util.Date;

import com.common.system.entity.WxDetailEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsDetailWxDTO {

	private WxDetailEntity wxDetailEntity;
	
	/**
	 * 創建時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * @return the wxDetailEntity
	 */
	public WxDetailEntity getWxDetailEntity() {
		return wxDetailEntity;
	}

	/**
	 * @param wxDetailEntity the wxDetailEntity to set
	 */
	public void setWxDetailEntity(WxDetailEntity wxDetailEntity) {
		this.wxDetailEntity = wxDetailEntity;
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
	

}
