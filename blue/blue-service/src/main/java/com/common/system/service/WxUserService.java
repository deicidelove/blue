package com.common.system.service;

import java.util.List;

import com.common.system.entity.WxUserEntity;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

public interface WxUserService {
	
	public Result<Integer> save(WxUserEntity wxUserEntity);
	
	public Result<Integer> deleteById(String openId);
	
	public void updateJifen(WxUserEntity wxUserEntity);
	
	public WxUserEntity getById(String openId);
	
	public PageInfo<WxUserEntity> listForPage(Integer pageNum, Integer pageSize);
	
	/**
	 * <p>
	 * <code>listForPage</code>
	 * </p>
	 * 通过superOpenId查询推荐人
	 * @author taowang6
	 * @param pageNum
	 * @param pageSize
	 * @param superOpenId
	 * @return
	 */
	public PageInfo<WxUserEntity> listForPage(Integer pageNum, Integer pageSize, String superOpenId);
	
	public List<WxUserEntity> list();
	
	/**
	 *  更新tip
	 * @param openId
	 * @param isShowTip
	 */
	public void updateTip(String openId, Boolean isShowTip);
}
