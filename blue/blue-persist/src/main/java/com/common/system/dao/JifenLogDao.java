package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.common.system.entity.JifenLogEntity;
import com.google.common.collect.Maps;

@Repository("jifenLogDao")
public class JifenLogDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public JifenLogEntity seleteById(Integer sid){
		Assert.notNull(sid,"sid is null");
		String sql = " SELECT * FROM rc_a_jifen_log WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		List<JifenLogEntity> resultList = namedParameterJdbcTemplate
				.query(sql, paramMap, new BeanPropertyRowMapper<JifenLogEntity>(JifenLogEntity.class));
		return !CollectionUtils.isEmpty(resultList) ? resultList.get(0): null;
	}
	
	public List<JifenLogEntity> seleteList(Integer pageNum, Integer pageSize, String openId){
		String sql = " SELECT * FROM rc_a_jifen_log WHERE open_id = :openId order by create_time desc limit :pageStartNum, :pageSize  ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		paramMap.put("openId", openId);
		List<JifenLogEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(JifenLogEntity.class));
		return result;
	}
	
	public JifenLogEntity seleteByType( String openId, String type){
		String sql = " SELECT * FROM rc_a_jifen_log WHERE open_id = :openId and type = :type limit 1 order by create_time desc ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("openId", openId);
		paramMap.put("type", type);
		List<JifenLogEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(JifenLogEntity.class));
		
		return !CollectionUtils.isEmpty(result) ? result.get(0): null;
	}
	
	public List<JifenLogEntity> seleteList(){
		String sql = " SELECT * FROM rc_a_jifen_log WHERE 1=1 order by create_time desc";
		Map<String, Object> paramMap = Maps.newHashMap();
		List<JifenLogEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(JifenLogEntity.class));
		return result;
	}
	
	public int save(JifenLogEntity jifenLogEntity){
		Assert.notNull(jifenLogEntity,"jifenLogEntity is null");
		String sql = " INSERT INTO rc_a_jifen_log "
				+ " (  open_id, jifen, type , is_reverse) "
				+ " VALUES ("
				+ ":openId, :jifen, :type, :isReverse"
				+ ")";
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(jifenLogEntity));
	}
	
	public void delete(Integer jifenLogId){
		Assert.notNull(jifenLogId,"jifenLogId is null");
		String sql = " delete from rc_a_jifen_log "
				+ "  where sid = :jifenLogId ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("jifenLogId", jifenLogId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
}
