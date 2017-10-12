package com.common.system.service;

import com.common.system.entity.GivingEntity;

public interface GivingService {
	
	public void saveGiving(GivingEntity givingEntity);
	
	public void deleteById(Integer givingId);
	
	public void update(GivingEntity givingEntity);
	
	public GivingEntity getById(Integer givingId);
	
}
