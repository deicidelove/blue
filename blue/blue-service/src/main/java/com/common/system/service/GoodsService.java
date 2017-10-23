package com.common.system.service;

import java.util.List;

import com.common.system.entity.GoodsEntity;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

public interface GoodsService {
	
	public Result<Integer> saveGoods(GoodsEntity goodsEntity);
	
	public Result<Integer> deleteById(Integer goodsId);
	
	public Result<Integer> update(GoodsEntity goodsEntity);
	
	public Result<GoodsEntity> getById(Integer goodsId);
	
	public List<GoodsEntity> list();
	
	public PageInfo<GoodsEntity> listForPage(Integer pageNum, Integer pageSize);
}
