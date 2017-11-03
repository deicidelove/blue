package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.GivingDao;
import com.common.system.entity.GivingEntity;
import com.common.system.service.GivingService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

@Service("givingService")
public class GivingServiceImpl implements GivingService {

	@Resource
	private GivingDao givingDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(GivingServiceImpl.class);
	
	@Override
	public void saveGiving(GivingEntity givingEntity) {
		
		try {
			givingDao.saveGiving(givingEntity);
			
		} catch (Exception e) {
			LOG.error("GivingServiceImpl saveAct error!", e );
		}
	}

	@Override
	public void deleteById(Integer actId) {
		
		try {
			givingDao.deleteById(actId);
			
		} catch (Exception e) {
			LOG.error("GivingServiceImpl deleteById error!", e );
		}
	}

	@Override
	public void update(GivingEntity givingEntity) {
		try {
			givingDao.update(givingEntity);
			
		} catch (Exception e) {
			LOG.error("GivingServiceImpl update error!", e );
		}

	}

	@Override
	public GivingEntity getById(Integer actId) {
		GivingEntity givingEntity = null;
		try {
			givingEntity = givingDao.seleteById(actId);
		} catch (Exception e) {
			LOG.error("ActServiceImpl getById error!", e );
		}
		return givingEntity;
	}

	@Override
	public PageInfo<GivingEntity> listForPage(Integer pageNum, Integer pageSize) {
		PageInfo<GivingEntity> result = new PageInfo<GivingEntity>();
		try {
			List<GivingEntity> resultList = givingDao.seleteList(pageNum, pageSize);
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error("ActServiceImpl deleteById error!", e );
		}
		return result;
	}

	@Override
	public List<GivingEntity> list() {
		List<GivingEntity> resultList = Lists.newArrayList();
		try {
			resultList = givingDao.seleteList();
		} catch (Exception e) {
			LOG.error("ActServiceImpl deleteById error!", e );
		}
		return resultList;
	}

}
