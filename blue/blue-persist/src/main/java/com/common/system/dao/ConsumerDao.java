package com.common.system.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.common.system.entity.ConsumerEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	活動表增刪改查
 */
@Repository("consumerDao")
public class ConsumerDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public ConsumerEntity seleteById(Integer consumerId){
		Assert.notNull(consumerId,"consumerId is null");
		String sql = " SELECT * FROM `rc_a_consumer`   WHERE consumer_id = :consumerId ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("consumerId", consumerId);
		ConsumerEntity result = namedParameterJdbcTemplate
				.queryForObject(sql, paramMap, new BeanPropertyRowMapper<ConsumerEntity>(ConsumerEntity.class));
				
		return result;
	}
	
	
	public void saveConsumer(ConsumerEntity consumerEntity){
		Assert.notNull(consumerEntity,"consumerEntity is null");
		String sql ="	INSERT INTO `rc_a_consumer` 	"
				+"	(`act_id`, `goods_id`, `giving_code`, `giving_code_source`)	"
				+ "	VALUES (:actId, :goodsId, :givingCode, :givingCodeSource)	";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(consumerEntity));
	}
	
	/**
	 * 刪除
	 * @param goodsId
	 */
	public void deleteById(Integer consumerId){
		Assert.notNull(consumerId,"consumerId is null");
		String sql = "DELETE FROM `rc_a_consumer` WHERE `consumer_id`=:consumerId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("consumerId", consumerId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	public void update(ConsumerEntity consumerEntity){
		Assert.notNull(consumerEntity,"consumerEntity is null");
		String sql = "	UPDATE `rc_a_consumer`  "
				+ "	SET `act_id`=:actId, `goods_id`=:goodsId, "
				+ "`giving_code`=:givingCode, `giving_code_source`=:givingCodeSource	"
				+ " WHERE `consumer_id`=:consumerId";
		Map<String,Object> paramMap = Maps.newHashMap();
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

}
