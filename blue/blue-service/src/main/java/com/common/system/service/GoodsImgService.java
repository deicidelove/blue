package com.common.system.service;


import java.util.List;

import com.common.system.entity.GoodsImgEntity;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;


public interface GoodsImgService {
	
	List<GoodsImgEntity> findByGoodsId(Integer goodsId, String imgType);
	
	GoodsImgEntity findById(Integer goodsImgId);
	
	PageInfo<GoodsImgEntity> listForPage(Integer pageNum, Integer pageSize, Integer goodsId);
	
	Result<Integer> save(GoodsImgEntity goodsImgEntity);
	
	Result<Integer> delete(Integer goodsImgId);
}
