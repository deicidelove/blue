package com.common.system.service;

import com.common.system.entity.ActEntity;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

public interface ActService {
	
	public Result<Integer> saveAct(ActEntity actEntity);
	
	public Result<Integer> deleteById(Integer actId);
	
	public void update(ActEntity actEntity);
	
	public ActEntity getById(Integer actId);
	
	public PageInfo<ActEntity> listForPage(Integer pageNum, Integer pageSize);
	
}
