package com.common.system.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class ActGoodsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8633300296726011682L;
	
	private Integer goodsId;
	
	private Integer actId;

	private String goodsName;
	
	private String goodsTitle;
	
	private BigDecimal goodsPrice;
	/**
	 * 活动总数
	 */
	private Integer actTotalNum;
	
	/**
	 *  剩余个数
	 */
	private Integer remainingNum;
	
	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the actTotalNum
	 */
	public Integer getActTotalNum() {
		return actTotalNum;
	}

	/**
	 * @param actTotalNum the actTotalNum to set
	 */
	public void setActTotalNum(Integer actTotalNum) {
		this.actTotalNum = actTotalNum;
	}

	/**
	 * @return the remainingNum
	 */
	public Integer getRemainingNum() {
		return remainingNum;
	}

	/**
	 * @param remainingNum the remainingNum to set
	 */
	public void setRemainingNum(Integer remainingNum) {
		this.remainingNum = remainingNum;
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
	 * @return the goodsPrice
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * @param goodsPrice the goodsPrice to set
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	/**
	 * @return the goodsTitle
	 */
	public String getGoodsTitle() {
		return goodsTitle;
	}

	/**
	 * @param goodsTitle the goodsTitle to set
	 */
	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}
	
}
