package com.common.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.system.dao.WxDetailDao;
import com.common.system.entity.WxDetailEntity;
import com.common.system.service.WxDetailService;

@Service("wxDetailService")
public class WxDetailServiceImpl implements WxDetailService {

	@Resource
	private WxDetailDao wxDetailDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(WxDetailServiceImpl.class);
	
	@Override
	public WxDetailEntity findByOpenId(String openId) {
		WxDetailEntity wxDetailEntity = null;
		try {
			wxDetailEntity = wxDetailDao.seleteById(openId);
		} catch (Exception e) {
			LOG.error("findByGoodsId error!", e);
		}
		return wxDetailEntity;
	}

	@Override
	public void save(WxDetailEntity wxDetailEntity) {
		try {
			wxDetailDao.saveWxDetailEntity(wxDetailEntity);
		} catch (Exception e) {
			LOG.error("findByGoodsId error!", e);
		}
	}

	@Override
	public void delete(String openId) {
		try {
			wxDetailDao.deleteById(openId);
		} catch (Exception e) {
			LOG.error("findByGoodsId error!", e);
		}
	}

}
