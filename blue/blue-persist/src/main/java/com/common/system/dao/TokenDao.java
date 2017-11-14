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

import com.common.system.entity.RcToken;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

@Repository("tokenDao")
public class TokenDao {
	
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public void insert(RcToken token) {
		Assert.notNull(token, "token should not be null");
		
		String sql = "insert into rc_token(id,app_id,token,expire_time) values(:id,:appId,:token,:expireTime)";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(token));
		
	}
	
	
	public RcToken getBeforeTime(String appId, Date time) {
		Assert.notNull(appId, "appId should not be null");
		Assert.notNull(time, "time should not be null");
		
		String sql = "select * from rc_token where app_id=:appId and expire_time>:time";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("appId", appId);
		paramMap.put("time", time);
		List<RcToken> tokens = namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<RcToken>(RcToken.class));
		if ( !CollectionUtils.isEmpty(tokens) ) {
			return tokens.get(0);
		} else {
			return null;
		}
	}
	
}