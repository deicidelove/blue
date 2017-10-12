package com.common.system.dto;

import com.common.system.entity.GoodsConsumerRelateEntity;

public class GoodsConsumerRelateDTO extends GoodsConsumerRelateEntity{

	private String goodsName;
	
	private String consumerName;
	
	private String actName;
	
	private Integer actPeriods;

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

	/**
	 * @return the actName
	 */
	public String getActName() {
		return actName;
	}

	/**
	 * @param actName the actName to set
	 */
	public void setActName(String actName) {
		this.actName = actName;
	}

	/**
	 * @return the actPeriods
	 */
	public Integer getActPeriods() {
		return actPeriods;
	}

	/**
	 * @param actPeriods the actPeriods to set
	 */
	public void setActPeriods(Integer actPeriods) {
		this.actPeriods = actPeriods;
	}
	
}
