package com.common.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.GoodsImgDao;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.service.GoodsImgService;

@Service("goodsImgService")
public class GoodsImgServiceImpl implements GoodsImgService {

	@Resource
	private GoodsImgDao goodsImgDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(GoodsImgServiceImpl.class);
	
	@Override
	public GoodsImgEntity findByGoodsId(Integer goodsId, String imgType) {
		
		
		GoodsImgEntity goodsImgEntity = null;
		
		try {
			goodsImgEntity = goodsImgDao.seleteById(goodsId, imgType);
		} catch (Exception e) {
			LOG.error("findByGoodsId error!",e);
		}
		return goodsImgEntity;
	}

	@Override
	public void save(GoodsImgEntity goodsImgEntity) {
		try {
			goodsImgDao.saveGoodsImg(goodsImgEntity);
		} catch (Exception e) {
			LOG.error("save goodsImg error!",e);
		}

	}

	@Override
	public void delete(Integer goodsImgId) {
		try {
			goodsImgDao.deleteById(goodsImgId);
		} catch (Exception e) {
			LOG.error("save goodsImg error!",e);
		}
	}

}
