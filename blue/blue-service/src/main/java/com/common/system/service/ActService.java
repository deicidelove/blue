package com.common.system.service;

import java.util.List;

import com.common.system.entity.ActEntity;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

public interface ActService {
	
	public Result<Integer> saveAct(ActEntity actEntity);
	
	public Result<Integer> deleteById(Integer actId);
	
	public Result<Integer> update(ActEntity actEntity);
	
	public ActEntity getById(Integer actId);
	
	public PageInfo<ActEntity> listForPage(Integer pageNum, Integer pageSize);
	
	public List<ActEntity> list( );
}
