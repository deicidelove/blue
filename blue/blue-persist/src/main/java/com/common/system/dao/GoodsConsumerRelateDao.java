package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.common.system.entity.GoodsConsumerRelateEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	活動表增刪改查
 */
@Repository("goodsConsumerRelateDao")
public class GoodsConsumerRelateDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public GoodsConsumerRelateEntity seleteById(Integer goodsConsumerId){
		Assert.notNull(goodsConsumerId,"goodsConsumerId is null");
		String sql = " SELECT * FROM `rc_a_goods_consumer_relate`  WHERE goods_consumer_id = :goodsConsumerId ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("goodsConsumerId", goodsConsumerId);
		GoodsConsumerRelateEntity result = namedParameterJdbcTemplate
				.queryForObject(sql, paramMap, new BeanPropertyRowMapper<GoodsConsumerRelateEntity>(GoodsConsumerRelateEntity.class));
				
		return result;
	}
	
	public List<GoodsConsumerRelateEntity> seleteList(Integer pageNum, Integer pageSize){
		String sql = " SELECT * FROM rc_a_goods_consumer_relate WHERE 1=1 limit :pageStartNum, :pageSize";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		List<GoodsConsumerRelateEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(GoodsConsumerRelateEntity.class));
		return result;
	}
	
	public List<GoodsConsumerRelateEntity> seleteListByActIdAndGoodsId(Integer actId, Integer goodsId){
		String sql = " SELECT * FROM rc_a_goods_consumer_relate WHERE 1=1 and act_Id = :actId and goods_Id = :goodsId";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("actId", actId);
		paramMap.put("goodsId", goodsId);
		List<GoodsConsumerRelateEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(GoodsConsumerRelateEntity.class));
		return result;
	}
	
	public void saveGoodsConsumerRelate(GoodsConsumerRelateEntity goodsConsumerRelateEntity){
		Assert.notNull(goodsConsumerRelateEntity,"goodsConsumerRelateEntity is null");
		String sql ="	INSERT INTO `rc_a_goods_consumer_relate`	"
				+"	(`act_id`, `goods_id`, `consumer_id`, `consumer_giving_code`)	"
				+ "	VALUES (:actId, :goodsId, :consumerId, :consumerGivingCode)	";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(goodsConsumerRelateEntity));
	}
	
	/**
	 * 刪除
	 * @param goodsId
	 */
	public void deleteById(Integer goodsConsumerId){
		Assert.notNull(goodsConsumerId,"goodsConsumerId is null");
		String sql = "DELETE FROM `rc_a_goods_consumer_relate` WHERE `goods_consumer_id`=:goodsConsumerId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("goodsConsumerId", goodsConsumerId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	public void update(GoodsConsumerRelateEntity goodsConsumerRelateEntity){
		Assert.notNull(goodsConsumerRelateEntity,"goodsConsumerRelateEntity is null");
		String sql = "	UPDATE `rc_a_goods_consumer_relate`  "
				+ "	SET `act_id`=:actId, `goods_id`=:goodsId, "
				+ "`user_id`=:consumerId, `consumer_giving_code`=:consumerGivingCode	"
				+ " WHERE `goods_consumer_id`=:goodsConsumerId";
		Map<String,Object> paramMap = Maps.newHashMap();
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

}
