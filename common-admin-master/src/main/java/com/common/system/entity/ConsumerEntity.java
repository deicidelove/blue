package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用戶表 
 * @author Blackgun
 *
 */
public class ConsumerEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6593822435526169887L;
	/**
	 *  用戶Id
	 */
	private Integer consumerId;
	
	/**
	 * 用户name
	 */
	private String consumerName;
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
	 * 中獎號碼
	 */
	private String givingCode;
	/**
	 * 中獎號碼來源
	 */
	private String givingCodeSource;
	
	/**
	 * 創建時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 更新時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGivingCode() {
		return givingCode;
	}
	public void setGivingCode(String givingCode) {
		this.givingCode = givingCode;
	}
	public String getGivingCodeSource() {
		return givingCodeSource;
	}
	public void setGivingCodeSource(String givingCodeSource) {
		this.givingCodeSource = givingCodeSource;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	 * @return the consumerName
	 */
	public String getConsumerName() {
		return consumerName;
	}
	/**
	 * @param consumerName the consumerName to set
	 */
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	

}
