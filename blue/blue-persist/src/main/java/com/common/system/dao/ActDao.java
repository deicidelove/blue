package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.common.system.entity.ActEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	活動表增刪改查
 */
@Repository("actDao")
public class ActDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public ActEntity seleteById(Integer actId){
		Assert.notNull(actId,"actId is null");
		String sql = " SELECT * FROM rc_a_act WHERE act_id = :actId ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("actId", actId);
		List<ActEntity> resultList = namedParameterJdbcTemplate
				.query(sql, paramMap, new BeanPropertyRowMapper<ActEntity>(ActEntity.class));
		return !CollectionUtils.isEmpty(resultList) ? resultList.get(0): null;
	}
	
	public List<ActEntity> seleteList(Integer pageNum, Integer pageSize){
		String sql = " SELECT * FROM rc_a_act WHERE act_is_delete=0 limit :pageStartNum, :pageSize";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		List<ActEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(ActEntity.class));
		return result;
	}
	
	public List<ActEntity> seleteList(){
		String sql = " SELECT * FROM rc_a_act WHERE act_is_delete=0 order by create_time desc";
		Map<String, Object> paramMap = Maps.newHashMap();
		List<ActEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(ActEntity.class));
		return result;
	}
	
	public int saveAct(ActEntity actEntity){
		Assert.notNull(actEntity,"actEntity is null");
		String sql = "INSERT INTO `rc_a_act` ("
				+ "`act_name`, `act_total_num`, `act_giving_num`, `act_periods`, `act_is_expire`, `act_is_delete`"
				+ ") VALUES ("
				+ ":actName, :actTotalNum, :actGivingNum, :actPeriods, :actIsExpire, :actIsDelete"
				+ ")";
		return namedParameterJdbcTemplate.update(sql, Convert.beanToMap(actEntity));
	}
	
	/**
	 * 刪除并非真正刪除
	 * @param actId
	 */
	public void deleteById(Integer actId){
		Assert.notNull(actId,"actId is null");
		String sql = "UPDATE `rc_a_act` SET `act_is_delete`='1' WHERE (`act_id`=:actId)";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("actId", actId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	public void update(ActEntity actEntity){
		Assert.notNull(actEntity,"actEntity is null");
		String sql = "UPDATE `rc_a_act` "
				+ "SET `act_name`=:actName, `act_total_num`=:actTotalNum, "
				+ "`act_periods`=:actPeriods, `act_is_expire`=:actIsExpire, "
				+ "`act_is_delete`=:actIsDelete WHERE `act_id`=:actId";
		
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(actEntity));
	}

}
