package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用戶參與的商品
 * @author Blackgun
 *
 */
public class GoodsConsumerRelateEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6276235225638792080L;

	/**
	 * 主鍵
	 */
	private Integer goodsConsumerId;
	
	/**
	 * 活動id
	 * 
	 */
	private Integer actId;
	
	/**
	 * 商品id
	 */
	private Integer goodsId;
	/**
	 * 用戶Id
	 */
	private Integer consumerId;
	/**
	 * 用戶參與的中獎號碼
	 */
	private String consumerGivingCode;
	
	/**
	 * 創建時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 微信唯一id，用户唯一标识
	 */
	private String openId;
	
	/**
	 * 用户幸运号来源 （wxPay，jifen）
	 */
	private String givingCodeSource;
	
	/**
	 * 是否被使用
	 */
	private Boolean isUsed;
	
	/**
	 * 乐观锁
	 */
	private Integer version;
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
	 * @return the consumerId
	 */
	public Integer getConsumerId() {
		return consumerId;
	}

	/**
	 * @param consumerId the consumerId to set
	 */
	public void setConsumerId(Integer consumerId) {
		this.consumerId = consumerId;
	}

	/**
	 * @return the consumerGivingCode
	 */
	public String getConsumerGivingCode() {
		return consumerGivingCode;
	}

	/**
	 * @param consumerGivingCode the consumerGivingCode to set
	 */
	public void setConsumerGivingCode(String consumerGivingCode) {
		this.consumerGivingCode = consumerGivingCode;
	}

	/**
	 * @return the givingCodeSource
	 */
	public String getGivingCodeSource() {
		return givingCodeSource;
	}

	/**
	 * @param givingCodeSource the givingCodeSource to set
	 */
	public void setGivingCodeSource(String givingCodeSource) {
		this.givingCodeSource = givingCodeSource;
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

	/**
	 * @return the isUsed
	 */
	public Boolean getIsUsed() {
		return isUsed;
	}

	/**
	 * @param isUsed the isUsed to set
	 */
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the goodsConsumerId
	 */
	public Integer getGoodsConsumerId() {
		return goodsConsumerId;
	}

	/**
	 * @param goodsConsumerId the goodsConsumerId to set
	 */
	public void setGoodsConsumerId(Integer goodsConsumerId) {
		this.goodsConsumerId = goodsConsumerId;
	}
	

}
