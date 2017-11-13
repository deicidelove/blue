package com.common.system.service;

import java.util.List;

import com.common.system.entity.LastActEntity;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

public interface LastActService {
	public Result<Integer> save(LastActEntity lastActEntity);
	
	public Result<Integer> deleteById(Integer lastActId);
	
	public Result<Integer> update(LastActEntity lastActEntity);
	
	public Result<LastActEntity> getById( Integer lastActId);
	
	public List<LastActEntity> list();
	
	public PageInfo<LastActEntity> listForPage(Integer pageNum, Integer pageSize);

}
