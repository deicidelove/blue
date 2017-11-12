package com.common.system.service;

import java.util.List;

import com.common.system.entity.JifenLogEntity;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

/**
 * 积分明细
 * @author Blackgun
 *
 */
public interface JifenLogService {
	public Result<Integer> save(JifenLogEntity jifenLogEntity);
	
	public JifenLogEntity getById(Integer jifenLogId);
	
	
	public PageInfo<JifenLogEntity> listForPage(Integer pageNum, Integer pageSize, String openId);
	
	public List<JifenLogEntity> list( );
	
	public void deleteById(Integer jifenLogId);
	
	public JifenLogEntity getByOpenIdAndType(String openId, String type);
	
}
