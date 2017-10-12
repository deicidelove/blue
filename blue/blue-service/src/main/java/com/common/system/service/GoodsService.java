package com.common.system.service;

import com.common.system.entity.GoodsEntity;

public interface GoodsService {
	
	public void saveGoods(GoodsEntity goodsEntity);
	
	public void deleteById(Integer goodsId);
	
	public void update(GoodsEntity goodsEntity);
	
	public GoodsEntity getById(Integer goodsId);
	
}
