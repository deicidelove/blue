package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.GoodsDao;
import com.common.system.entity.GoodsEntity;
import com.common.system.service.GoodsService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsDao goodsDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(GoodsServiceImpl.class);
	
	@Override
	public Result<Integer> saveGoods(GoodsEntity goodsEntity) {
		Result<Integer> result = new Result<Integer>();
		try {
			Integer goodsId = goodsDao.saveGoods(goodsEntity);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
            result.setData(goodsId);
		} catch (Exception e) {
			LOG.error("GivingServiceImpl saveAct error!", e );
		}
		return result;
	}

	@Override
	public Result<Integer> deleteById(Integer actId) {
		Result<Integer> result = new Result<Integer>();
		try {
			goodsDao.deleteById(actId);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("GivingServiceImpl deleteById error!", e );
		}
		return result;
	}

	@Override
	public Result<Integer> update(GoodsEntity goodsEntity) {
		Result<Integer> result = new Result<Integer>();
		try {
			goodsDao.update(goodsEntity);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("GivingServiceImpl update error!", e );
		}
		return result;
	}

	@Override
	public Result<GoodsEntity> getById(Integer goodsId) {
		Result<GoodsEntity> result = new Result<GoodsEntity>();
		try {
			GoodsEntity goodsEntity = goodsDao.seleteById(goodsId);
	        if (goodsEntity != null){
	            result.setData(goodsEntity);
	            result.setStatus(true);
	            result.setCode(MsgCode.SUCCESS);
	            result.setMsg("操作成功");
	        }
		} catch (Exception e) {
			LOG.error("ActServiceImpl getById error!", e );
		}
		return result;
	}

	@Override
	public PageInfo<GoodsEntity> listForPage(Integer pageNum, Integer pageSize) {
		PageInfo<GoodsEntity> result = new PageInfo<GoodsEntity>();
		try {
			List<GoodsEntity> resultList = goodsDao.seleteByList(pageNum, pageSize);
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error("ActServiceImpl listForPage error!", e );
		}
		return result;
	}

	@Override
	public List<GoodsEntity> list() {
		List<GoodsEntity> resultList = null;
		try {
			resultList = goodsDao.seleteByList();
		} catch (Exception e) {
			LOG.error("ActServiceImpl list error!", e );
		}
		return resultList;
	}

	@Override
	public List<GoodsEntity> listNoneOverGoods() {
		List<GoodsEntity> resultList = null;
		try {
			resultList = goodsDao.listNoneOverGoods();
		} catch (Exception e) {
			LOG.error("ActServiceImpl listNoneOverGoods error!", e );
		}
		return resultList;
	}

	@Override
	public void updateActGoodsOver(Integer goodsId) {
		goodsDao.updateActGoodsOver(goodsId);
	}

}
