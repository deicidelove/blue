package com.common.system.service;

import java.util.List;

import com.common.system.entity.GivingEntity;
import com.github.pagehelper.PageInfo;

public interface GivingService {
	
	public void saveGiving(GivingEntity givingEntity);
	
	public void deleteById(Integer givingId);
	
	public void update(GivingEntity givingEntity);
	
	public GivingEntity getById(Integer givingId);
	
	public List<GivingEntity> list();
	
	public PageInfo<GivingEntity> listForPage(Integer pageNum, Integer pageSize);
	
}
