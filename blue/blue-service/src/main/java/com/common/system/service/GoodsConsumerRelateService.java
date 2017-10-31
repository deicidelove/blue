package com.common.system.service;

import java.util.List;

import com.common.system.dto.GoodsConsumerRelateDTO;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.github.pagehelper.PageInfo;

public interface GoodsConsumerRelateService {
	
	public void saveGoodsConsumerRelate(GoodsConsumerRelateEntity goodsConsumerRelateEntity);
	
	public void deleteById(Integer goodsConsumerId);
	
	public void update(GoodsConsumerRelateEntity goodsConsumerRelateEntity);
	
	public GoodsConsumerRelateEntity getById(Integer goodsConsumerId);
	
	public PageInfo<GoodsConsumerRelateDTO> listForPage(Integer pageNum, Integer pageSize);
	
	public List<GoodsConsumerRelateEntity> list(Integer actId, Integer goodsId);
	
}
