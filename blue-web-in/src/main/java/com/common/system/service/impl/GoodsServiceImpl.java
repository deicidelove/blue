package com.common.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.GoodsDao;
import com.common.system.entity.GoodsEntity;
import com.common.system.service.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsDao goodsDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(GoodsServiceImpl.class);
	
	@Override
	public void saveGoods(GoodsEntity goodsEntity) {
		
		try {
			goodsDao.saveGoods(goodsEntity);
			
		} catch (Exception e) {
			LOG.error("GivingServiceImpl saveAct error!", e );
		}
	}

	@Override
	public void deleteById(Integer actId) {
		
		try {
			goodsDao.deleteById(actId);
			
		} catch (Exception e) {
			LOG.error("GivingServiceImpl deleteById error!", e );
		}
	}

	@Override
	public void update(GoodsEntity goodsEntity) {
		try {
			goodsDao.update(goodsEntity);
			
		} catch (Exception e) {
			LOG.error("GivingServiceImpl update error!", e );
		}

	}

	@Override
	public GoodsEntity getById(Integer goodsId) {
		GoodsEntity goodsEntity = null;
		try {
			goodsEntity = goodsDao.seleteById(goodsId);
		} catch (Exception e) {
			LOG.error("ActServiceImpl getById error!", e );
		}
		return goodsEntity;
	}

}
