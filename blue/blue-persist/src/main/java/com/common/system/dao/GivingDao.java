package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.common.system.entity.GivingEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	活動表增刪改查
 */
@Repository("givingDao")
public class GivingDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public GivingEntity seleteById(Integer givingId){
		Assert.notNull(givingId,"givingId is null");
		String sql = " SELECT * FROM rc_a_act WHERE giving_id = :givingId ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("givingId", givingId);
		GivingEntity result = namedParameterJdbcTemplate
				.queryForObject(sql, paramMap, new BeanPropertyRowMapper<GivingEntity>(GivingEntity.class));
				
		return result;
	}
	
	
	public void saveGiving(GivingEntity givingEntity){
		Assert.notNull(givingEntity,"givingEntity is null");
		String sql ="	INSERT INTO `rc_a_giving`	"
				+"	(`act_id`, `goods_id`, `giving_consumer_id`, `giving_code`)	"
				+ "	VALUES (:actId, :goodsId, :givingConsumerId, :givingCode)	";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(givingEntity));
	}
	
	/**
	 * 刪除
	 * @param givingId
	 */
	public void deleteById(Integer givingId){
		Assert.notNull(givingId,"givingId is null");
		String sql = "DELETE FROM `rc_a_giving` WHERE `giving_id`=:givingId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("givingId", givingId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	public void update(GivingEntity givingEntity){
		Assert.notNull(givingEntity,"givingEntity is null");
		String sql = "	UPDATE `rc_a_giving`  "
				+ "	SET `act_id`=:actId, `goods_id`=:goodsId, "
				+ "`giving_consumer_id`=:givingConsumerId, `giving_code`=:givingCode	"
				+ " WHERE `giving_id`=:givingId";
		Map<String,Object> paramMap = Maps.newHashMap();
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	public List<GivingEntity> seleteList(Integer pageNum, Integer pageSize){
		String sql = " SELECT * FROM rc_a_giving WHERE 1=1 order by createTime desc limit :pageStartNum, :pageSize ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		List<GivingEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(GivingEntity.class));
		return result;
	}
	
	public List<GivingEntity> seleteList(){
		String sql = " SELECT * FROM rc_a_giving WHERE 1=1 order by createTime desc";
		Map<String, Object> paramMap = Maps.newHashMap();
		List<GivingEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(GivingEntity.class));
		return result;
	}
	
}
