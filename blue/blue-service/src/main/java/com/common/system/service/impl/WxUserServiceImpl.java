package com.common.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.WxuserDao;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.WxUserService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {

	@Resource
	private WxuserDao wxuserDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(WxUserServiceImpl.class);
	@Override
	public Result<Integer> save(WxUserEntity wxUserEntity) {
		Result<Integer> result = new Result<Integer>();
		try {
			wxuserDao.save(wxUserEntity);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("WxUserServiceImpl save error!", e );
		}
		return result;
	}

	@Override
	public Result<Integer> deleteById(String openId) {
		Result<Integer> result = new Result<Integer>();
		try {
			wxuserDao.deleteById(openId);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			LOG.error("WxUserServiceImpl deleteById error!", e );
		}
		return result;
	}

	@Override
	public void updateJifen(WxUserEntity wxUserEntity) {
		try {
			wxuserDao.updateJifen(wxUserEntity);
		} catch (Exception e) {
			LOG.error("WxUserServiceImpl updateJifen error!", e );
		}

	}

	@Override
	public WxUserEntity getById(String openId) {
		WxUserEntity wxUserEntity = null;
		try {
			wxUserEntity = wxuserDao.seleteById(openId);
		} catch (Exception e) {
			LOG.error("WxUserServiceImpl getById error!", e );
		}
		return wxUserEntity;
	}

	@Override
	public PageInfo<WxUserEntity> listForPage(Integer pageNum, Integer pageSize) {
		PageInfo<WxUserEntity> result = new PageInfo<WxUserEntity>();
		try {
			List<WxUserEntity> resultList = wxuserDao.seleteList(pageNum, pageSize);
			result.setList(resultList);
		} catch (Exception e) {
			LOG.error("WxUserServiceImpl listForPage error!", e );
		}
		return result;
	}

	@Override
	public List<WxUserEntity> list() {
		List<WxUserEntity> resultList = Lists.newArrayList();
		try {
			resultList = wxuserDao.seleteList();
		} catch (Exception e) {
			LOG.error("WxUserServiceImpl list error!", e );
		}
		return resultList;
	}

	@Override
	public void updateTip(String openId, Boolean isShowTip) {
		try {
			wxuserDao.updateTip(openId, isShowTip);
		} catch (Exception e) {
			LOG.error("updateTip error!", e);
		}
	}

}
