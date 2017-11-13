package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.LastActDao;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.LastActEntity;
import com.common.system.service.LastActService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
@Service("lastActService")
public class LastActServiceImpl implements LastActService {

	@Resource
	private LastActDao lastActDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(LastActServiceImpl.class);
	
	@Override
	public Result<Integer> save(LastActEntity lastActEntity) {
		Result<Integer> result = new Result<Integer>();
		try {
			Integer lastActId = lastActDao.save(lastActEntity);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
            result.setData(lastActId);
		} catch (Exception e) {
			LOG.error("LastActServiceImpl saveAct error!", e );
		}
		return result;
	}

	@Override
	public Result<Integer> deleteById(Integer lastActId) {
		Result<Integer> result = new Result<Integer>();
		try {
			lastActDao.deleteById(lastActId);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("LastActServiceImpl deleteById error!", e );
		}
		return result;
	}

	@Override
	public Result<Integer> update(LastActEntity lastActEntity) {
		Result<Integer> result = new Result<Integer>();
		try {
			lastActDao.update(lastActEntity);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("LastActServiceImpl update error!", e );
		}
		return result;
	}

	@Override
	public Result<LastActEntity> getById(Integer lastActId) {
		Result<LastActEntity> result = new Result<LastActEntity>();
		try {
			LastActEntity lastActEntity = lastActDao.seleteById(lastActId);
	        if (lastActEntity != null){
	            result.setData(lastActEntity);
	            result.setStatus(true);
	            result.setCode(MsgCode.SUCCESS);
	            result.setMsg("操作成功");
	        }
		} catch (Exception e) {
			LOG.error("LastActServiceImpl getById error!", e );
		}
		return result;
	}

	@Override
	public List<LastActEntity> list() {
		List<LastActEntity> resultList = null;
		try {
			resultList = lastActDao.seleteList();
		} catch (Exception e) {
			LOG.error("LastActServiceImpl list error!", e );
		}
		return resultList;
	}

	@Override
	public PageInfo<LastActEntity> listForPage(Integer pageNum, Integer pageSize) {
		PageInfo<LastActEntity> result = new PageInfo<LastActEntity>();
		try {
			List<LastActEntity> resultList = lastActDao.seleteList(pageNum, pageSize);
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error("LastActServiceImpl listForPage error!", e );
		}
		return result;
	}

}
