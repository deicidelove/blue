package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 中獎表
 * @author Blackgun
 *
 */
public class GivingEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8542480264232092836L;
	/**
	 * 中獎Id
	 */
	private Integer givingId;
	/**
	 * 活動id
	 * 
	 */
	private Integer actId;
	/**
	 * 商品Id
	 */
	private Integer goodsId;
	/**
	 * 中獎用戶
	 */
	private Integer givingConsumerId;
	/**
	 * 中獎號碼
	 */
	private String givingCode;
	/**
	 * 創建時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/**
	 * 微信唯一标识
	 */
	private String openId;
	/**
	 * @return the givingId
	 */
	public Integer getGivingId() {
		return givingId;
	}
	/**
	 * @param givingId the givingId to set
	 */
	public void setGivingId(Integer givingId) {
		this.givingId = givingId;
	}
	/**
	 * @return the actId
	 */
	public Integer getActId() {
		return actId;
	}
	/**
	 * @param actId the actId to set
	 */
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	/**
	 * @return the goodsId
	 */
	public Integer getGoodsId() {
		return goodsId;
	}
	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * @return the givingCode
	 */
	public String getGivingCode() {
		return givingCode;
	}
	/**
	 * @param givingCode the givingCode to set
	 */
	public void setGivingCode(String givingCode) {
		this.givingCode = givingCode;
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
	 * @return the givingConsumerId
	 */
	public Integer getGivingConsumerId() {
		return givingConsumerId;
	}
	/**
	 * @param givingConsumerId the givingConsumerId to set
	 */
	public void setGivingConsumerId(Integer givingConsumerId) {
		this.givingConsumerId = givingConsumerId;
	}
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
}
