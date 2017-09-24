package com.common.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.ConsumerDao;
import com.common.system.entity.ConsumerEntity;
import com.common.system.service.ConsumerService;

@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService {

	@Resource
	private ConsumerDao consumerDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsumerServiceImpl.class);
	
	@Override
	public void saveConsumer(ConsumerEntity consumerEntity) {
		
		try {
			consumerDao.saveConsumer(consumerEntity);
			
		} catch (Exception e) {
			LOG.error("ConsumerServiceImpl saveAct error!", e );
		}
	}

	@Override
	public void deleteById(Integer consumerId) {
		
		try {
			consumerDao.deleteById(consumerId);
			
		} catch (Exception e) {
			LOG.error("ConsumerServiceImpl deleteById error!", e );
		}
	}

	@Override
	public void update(ConsumerEntity consumerEntity) {
		try {
			consumerDao.update(consumerEntity);
			
		} catch (Exception e) {
			LOG.error("ConsumerServiceImpl update error!", e );
		}

	}

	@Override
	public ConsumerEntity getById(Integer actId) {
		ConsumerEntity consumerEntity = null;
		try {
			consumerEntity = consumerDao.seleteById(actId);
		} catch (Exception e) {
			LOG.error("ConsumerServiceImpl getById error!", e );
		}
		return consumerEntity;
	}

}
