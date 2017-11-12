package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.JifenLogDao;
import com.common.system.entity.JifenLogEntity;
import com.common.system.service.JifenLogService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;


@Service("jifenLogService")
public class JifenLogServiceImpl implements JifenLogService{

	@Resource
	private JifenLogDao jifenLogDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(JifenLogServiceImpl.class);
	
	@Override
	public Result<Integer> save(JifenLogEntity jifenLogEntity) {
		Result<Integer> result = new Result<Integer>();
		try {
			jifenLogDao.save(jifenLogEntity);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("JifenLogServiceImpl save error!", e );
		}
		return result;
	}

	@Override
	public JifenLogEntity getById(Integer actId) {
		JifenLogEntity jifenLogEntity = null;
		try {
			jifenLogEntity = jifenLogDao.seleteById(actId);
		} catch (Exception e) {
			LOG.error("JifenLogServiceImpl getById error!", e );
		}
		return jifenLogEntity;
	}

	@Override
	public PageInfo<JifenLogEntity> listForPage(Integer pageNum,
			Integer pageSize, String openId) {
		PageInfo<JifenLogEntity> result = new PageInfo<JifenLogEntity>();
		try {
			List<JifenLogEntity> resultList = jifenLogDao.seleteList(pageNum, pageSize, openId);
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error("JifenLogServiceImpl listForPage error!", e );
		}
		return result;
	}

	@Override
	public List<JifenLogEntity> list() {
		List<JifenLogEntity> resultList = Lists.newArrayList();
		try {
			resultList = jifenLogDao.seleteList();
		} catch (Exception e) {
			LOG.error("JifenLogServiceImpl list error!", e );
		}
		return resultList;
	}

	@Override
	public void deleteById(Integer jifenLogId) {
		try {
			jifenLogDao.delete(jifenLogId);
		} catch (Exception e) {
			LOG.error("JifenLogServiceImpl deleteById error!", e );
		}
		
	}

	@Override
	public JifenLogEntity getByOpenIdAndType(String openId, String type) {
		JifenLogEntity jifenLogEntity = null;
		try {
			jifenLogEntity = jifenLogDao.seleteByType(openId, type);
		} catch (Exception e) {
			LOG.error("JifenLogServiceImpl getByOpenIdAndType error!", e );
		}
		return jifenLogEntity;
	}

}
