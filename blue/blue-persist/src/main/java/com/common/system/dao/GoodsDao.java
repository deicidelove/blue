package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import com.common.system.entity.GoodsEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	活動表增刪改查
 */
@Repository("goodsDao")
public class GoodsDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public GoodsEntity seleteById(Integer goodsId){
		Assert.notNull(goodsId,"goodsId is null");
		String sql = " SELECT * FROM `rc_a_goods`  WHERE goods_id = :goodsId ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("goodsId", goodsId);
		GoodsEntity result = namedParameterJdbcTemplate
				.queryForObject(sql, paramMap, new BeanPropertyRowMapper<GoodsEntity>(GoodsEntity.class));
				
		return result;
	}
	
	public List<GoodsEntity> seleteByList(Integer pageNum,
			Integer pageSize){
		String sql = " SELECT * FROM rc_a_goods WHERE 1=1 limit :pageStartNum, :pageSize";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		List<GoodsEntity> resultList = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(GoodsEntity.class));
		return resultList;
	}
	
	public List<GoodsEntity> seleteByList(){
		String sql = " SELECT * FROM rc_a_goods WHERE 1=1 ";
		Map<String, Object> paramMap = Maps.newHashMap();
		List<GoodsEntity> resultList = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(GoodsEntity.class));
		return resultList;
	}
	
	public void saveGoods(GoodsEntity goodsEntity){
		Assert.notNull(goodsEntity,"goodsEntity is null");
		String sql ="	INSERT INTO `rc_a_goods`	"
				+"	( act_id, goods_name, goods_price,  goods_title, goods_detail, is_delete, category, jifen)	"
				+ "	VALUES (:actId, :goodsName,:goodsTitle,:goodsDetail,:isDelete, :category, :jifen)	";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(goodsEntity));
	}
	
	/**
	 * 刪除
	 * @param goodsId
	 */
	public void deleteById(Integer goodsId){
		Assert.notNull(goodsId,"goodsId is null");
		String sql = "DELETE FROM `rc_a_goods` WHERE `goods_id`=:goodsId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("goodsId", goodsId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	public void update(GoodsEntity goodsEntity){
		Assert.notNull(goodsEntity,"goodsEntity is null");
		String sql = "	UPDATE `rc_a_goods`  "
				+ "	SET `act_id`=:actId, `goods_name`=:goodsName "
				+ " WHERE `goods_id`=:goodsId";
		Map<String,Object> paramMap = Maps.newHashMap();
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

}
