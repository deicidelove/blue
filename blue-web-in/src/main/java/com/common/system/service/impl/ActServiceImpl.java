package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.ActDao;
import com.common.system.entity.ActEntity;
import com.common.system.service.ActService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

@Service("actService")
public class ActServiceImpl implements ActService {

	@Resource
	private ActDao actDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(ActServiceImpl.class);
	
	@Override
	public Result<Integer> saveAct(ActEntity actEntity) {
		Result<Integer> result = new Result<Integer>();
		try {
			actDao.saveAct(actEntity);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("ActServiceImpl saveAct error!", e );
		}
		return result;
	}

	@Override
	public Result<Integer> deleteById(Integer actId) {
		Result<Integer> result = new Result<Integer>();
		try {
			actDao.deleteById(actId);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("ActServiceImpl deleteById error!", e );
		}
		return result;
	}

	@Override
	public void update(ActEntity actEntity) {
		try {
			actDao.update(actEntity);
			
		} catch (Exception e) {
			LOG.error("ActServiceImpl update error!", e );
		}

	}

	@Override
	public ActEntity getById(Integer actId) {
		ActEntity actEntity = null;
		try {
			actEntity = actDao.seleteById(actId);
		} catch (Exception e) {
			LOG.error("ActServiceImpl getById error!", e );
		}
		return actEntity;
	}

	@Override
	public PageInfo<ActEntity> listForPage(Integer pageNum, Integer pageSize) {
		PageInfo<ActEntity> result = new PageInfo<ActEntity>();
		try {
			List<ActEntity> resultList = actDao.seleteList(pageNum, pageSize);
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error("ActServiceImpl deleteById error!", e );
		}
		return result;
	}

}
