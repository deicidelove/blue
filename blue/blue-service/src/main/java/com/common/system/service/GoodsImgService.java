package com.common.system.service;


import com.common.system.entity.GoodsImgEntity;


public interface GoodsImgService {
	
	GoodsImgEntity findByGoodsId(Integer goodsId, String imgType);
	
	void save(GoodsImgEntity goodsImgEntity);
	
	void delete(Integer goodsImgId);
}
