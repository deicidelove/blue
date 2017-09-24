package com.common.system.service;

import com.common.system.entity.ConsumerEntity;

public interface ConsumerService {
	
	public void saveConsumer(ConsumerEntity consumerEntity);
	
	public void deleteById(Integer consumerId);
	
	public void update(ConsumerEntity consumerEntity);
	
	public ConsumerEntity getById(Integer consumerId);
	
}
