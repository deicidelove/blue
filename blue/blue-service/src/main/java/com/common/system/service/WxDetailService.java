package com.common.system.service;

import com.common.system.entity.WxDetailEntity;

/**
 * 微信用户信息
 * @author Blackgun
 *
 */
public interface WxDetailService {
	
	
	WxDetailEntity findByOpenId(String openId);
	
	void save(WxDetailEntity wxDetailEntity);
	
	void delete(String openId);
}
