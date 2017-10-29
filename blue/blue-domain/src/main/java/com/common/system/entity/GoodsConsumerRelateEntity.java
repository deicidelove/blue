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
	private Integer goodsconsumerId;
	
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
	 * @return the goodsconsumerId
	 */
	public Integer getGoodsconsumerId() {
		return goodsconsumerId;
	}

	/**
	 * @param goodsconsumerId the goodsconsumerId to set
	 */
	public void setGoodsconsumerId(Integer goodsconsumerId) {
		this.goodsconsumerId = goodsconsumerId;
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
	

}
