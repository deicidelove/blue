package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.GoodsImgDao;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.service.GoodsImgService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

@Service("goodsImgService")
public class GoodsImgServiceImpl implements GoodsImgService {

	@Resource
	private GoodsImgDao goodsImgDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(GoodsImgServiceImpl.class);
	
	@Override
	public List<GoodsImgEntity> findByGoodsId(Integer goodsId, String imgType) {
		
		
		List<GoodsImgEntity> goodsImgEntityList = null;
		
		try {
			goodsImgEntityList = goodsImgDao.seleteByGoodsId(goodsId, imgType);
		} catch (Exception e) {
			LOG.error("findByGoodsId error!",e);
		}
		return goodsImgEntityList;
	}

	@Override
	public Result<Integer> save(GoodsImgEntity goodsImgEntity) {
		Result<Integer> result = new Result<Integer>();
		try {
			goodsImgDao.saveGoodsImg(goodsImgEntity);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("save goodsImg error!",e);
            result.setStatus(false);
            result.setCode(MsgCode.FAILED);
            result.setMsg("系统异常,请联系管理员!");			
		}
		return result;
	}

	@Override
	public Result<Integer> delete(Integer goodsImgId) {
		Result<Integer> result = new Result<Integer>();
		try {
			goodsImgDao.deleteById(goodsImgId);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("delete goodsImg error!",e);
            result.setStatus(false);
            result.setCode(MsgCode.FAILED);
            result.setMsg("系统异常,请联系管理员!");			
		}
		return result;
	}

	@Override
	public PageInfo<GoodsImgEntity> listForPage(Integer pageNum, Integer pageSize,
			Integer goodsId) {
		PageInfo<GoodsImgEntity> goodsImgPage = new PageInfo<GoodsImgEntity>();
		try {
			List<GoodsImgEntity> goodsImgEntityList = goodsImgDao.seleteByList(pageNum, pageSize, goodsId);
			goodsImgPage.setList(goodsImgEntityList);
		} catch (Exception e) {
			LOG.error("listForPage goodsImg error!",e);
		}
		return goodsImgPage;
	}

	@Override
	public GoodsImgEntity findById(Integer goodsImgId) {
		GoodsImgEntity goodsImgEntity = null;
		try {
			goodsImgEntity = goodsImgDao.seleteById(goodsImgId);
		} catch (Exception e) {
			LOG.error("findById error!",e);
		}
		return goodsImgEntity;
	}

}
