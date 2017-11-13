package com.common.system.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.common.system.entity.MsgVerify;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

@Repository("msgVerifyDao")
public class MsgVerifyDao {
	
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	
	
	public void insert(MsgVerify verify) {
		String sql = "insert into tb_msg_verify(phoneNumber, checkCode, expireTime) values (:phoneNumber, :checkCode, :expireTime)";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(verify));
	}
	
	
	public MsgVerify getUnderTime(String phoneNumber, Date curTime) {
		Assert.notNull(phoneNumber, "Parameter openId should not be null");
		
		String sql = "select * from tb_msg_verify where phoneNumber=:phoneNumber and expireTime>:curTime";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("phoneNumber", phoneNumber);
		paramMap.put("curTime", curTime);
		List<MsgVerify> verifies = namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(MsgVerify.class));
		if ( !CollectionUtils.isEmpty(verifies) ) {
			return verifies.get(0);
		} else {
			return null;
		}
	}
	
	
}
