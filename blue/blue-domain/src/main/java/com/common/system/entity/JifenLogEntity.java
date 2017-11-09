package com.common.system.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 积分日志
 * <p>
 * <code>JifenLogEntity</code>
 * </p>
 *
 * @author taowang6
 * @since 1.0
 * @version 1.0
 */
public class JifenLogEntity {
	/**
	 * 微信唯一标识
	 */
	private String openId;
	
	/**
	 * 积分
	 */
	private Integer jifen;
	
	/**
	 * 积分类型
	 */
	private String type;
	
	/**
	 * 是否逆向
	 */
	private Boolean isReverse;
	/**
	 * 創建時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the jifen
	 */
	public Integer getJifen() {
		return jifen;
	}

	/**
	 * @param jifen the jifen to set
	 */
	public void setJifen(Integer jifen) {
		this.jifen = jifen;
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
	 * @return the isReverse
	 */
	public Boolean getIsReverse() {
		return isReverse;
	}

	/**
	 * @param isReverse the isReverse to set
	 */
	public void setIsReverse(Boolean isReverse) {
		this.isReverse = isReverse;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
