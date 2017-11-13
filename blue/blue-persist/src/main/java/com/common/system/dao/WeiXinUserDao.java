package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.common.system.entity.RcWeiXinUser;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

@Repository("weiXinUserDao")
public class WeiXinUserDao {
	
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public void insert(RcWeiXinUser user) {
		Assert.notNull(user, "user should not be null");
		
		String sql = "insert into rc_a_wx_user(openId,tel,userName,ticket,qrcodeUrl,inviteCount,createTime) "
				+ "values(:openId,:tel,:userName,:ticket,:qrcodeUrl,:inviteCount,:createTime) "
				+ "on duplicate key update createTime=:createTime";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(user));
	}
	
	
	
	public void updateUserInfo(String openId, String phoneNumber, String userName, String ticket, String qrcodeUrl) {
		Assert.notNull(openId, "openId should not be null");
		Assert.notNull(phoneNumber, "phoneNumber should not be null");
		Assert.notNull(ticket, "ticket should not be null");
		Assert.notNull(qrcodeUrl, "qrcodeUrl should not be null");
		
		String sql = "update rc_a_wx_user set tel=:tel, userName=:userName, ticket=:ticket, qrcodeUrl=:qrcodeUrl where openId=:openId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("tel", phoneNumber);
		paramMap.put("userName", userName);
		paramMap.put("ticket", ticket);
		paramMap.put("openId", openId);
		paramMap.put("qrcodeUrl", qrcodeUrl);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	
	public RcWeiXinUser getUserOpenId(String openId) {
		Assert.notNull(openId, "openId should not be null");
		
		String sql = "select * from rc_a_wx_user where openId=:openId limit 1";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("openId", openId);
		List<RcWeiXinUser> users = namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<RcWeiXinUser>(RcWeiXinUser.class));
		if ( !CollectionUtils.isEmpty(users) ) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	
	public void increaseInviteCountByTickt(String ticket) {
		Assert.notNull(ticket, "ticket should not be null");
		
		String sql = "update rc_a_wx_user set inviteCount=inviteCount+1 where ticket=:ticket";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("ticket", ticket);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
}
