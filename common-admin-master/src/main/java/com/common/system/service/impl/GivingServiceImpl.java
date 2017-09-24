package com.common.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.GivingDao;
import com.common.system.entity.GivingEntity;
import com.common.system.service.GivingService;

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

}
