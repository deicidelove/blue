package com.common.system.dto;

import com.common.system.entity.GoodsEntity;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.entity.OrderEntity;

public class PersonalOrderDTO {
	
	private GoodsEntity goodsEntity;
	
	private OrderEntity orderEntity;
	
	private GoodsImgEntity goodsImgEntity ;
	/**
	 * @return the goodsEntity
	 */
	public GoodsEntity getGoodsEntity() {
		return goodsEntity;
	}

	/**
	 * @param goodsEntity the goodsEntity to set
	 */
	public void setGoodsEntity(GoodsEntity goodsEntity) {
		this.goodsEntity = goodsEntity;
	}

	/**
	 * @return the orderEntity
	 */
	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	/**
	 * @param orderEntity the orderEntity to set
	 */
	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

	/**
	 * @return the goodsImgEntity
	 */
	public GoodsImgEntity getGoodsImgEntity() {
		return goodsImgEntity;
	}

	/**
	 * @param goodsImgEntity the goodsImgEntity to set
	 */
	public void setGoodsImgEntity(GoodsImgEntity goodsImgEntity) {
		this.goodsImgEntity = goodsImgEntity;
	}

}
