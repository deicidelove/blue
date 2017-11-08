package com.common.system.service;


import java.util.List;

import com.common.system.entity.GoodsImgEntity;


public interface GoodsImgService {
	
	List<GoodsImgEntity> findByGoodsId(Integer goodsId, String imgType);
	
	void save(GoodsImgEntity goodsImgEntity);
	
	void delete(Integer goodsImgId);
}
