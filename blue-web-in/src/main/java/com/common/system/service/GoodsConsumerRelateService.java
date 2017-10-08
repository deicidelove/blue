package com.common.system.service;

import com.common.system.dto.GoodsConsumerRelateDTO;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.github.pagehelper.PageInfo;

public interface GoodsConsumerRelateService {
	
	public void saveGoodsConsumerRelate(GoodsConsumerRelateEntity goodsConsumerRelateEntity);
	
	public void deleteById(Integer actId);
	
	public void update(GoodsConsumerRelateEntity goodsConsumerRelateEntity);
	
	public GoodsConsumerRelateEntity getById(Integer actId);
	
	public PageInfo<GoodsConsumerRelateDTO> listForPage(Integer pageNum, Integer pageSize);
	
}
