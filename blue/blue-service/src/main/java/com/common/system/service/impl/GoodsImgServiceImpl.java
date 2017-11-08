package com.common.system.service.impl;

import java.util.List;

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
	public List<GoodsImgEntity> findByGoodsId(Integer goodsId, String imgType) {
		
		
		List<GoodsImgEntity> goodsImgEntityList = null;
		
		try {
			goodsImgEntityList = goodsImgDao.seleteById(goodsId, imgType);
		} catch (Exception e) {
			LOG.error("findByGoodsId error!",e);
		}
		return goodsImgEntityList;
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
