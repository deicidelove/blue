package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.common.system.entity.GoodsImgEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	图片表增刪改查
 */
@Repository("goodsImgDao")
public class GoodsImgDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public List<GoodsImgEntity> seleteById(Integer goodsId, String imgType){
		Assert.notNull(goodsId,"goodsId is null");
		Assert.notNull(imgType,"imgType is null");
		String sql = " SELECT * FROM `rc_a_goods_img`  WHERE goods_id = :goodsId and img_type = :imgType ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("goodsId", goodsId);
		
		paramMap.put("imgType", imgType);
		List<GoodsImgEntity> resultList = namedParameterJdbcTemplate
				.query(sql, paramMap, new BeanPropertyRowMapper<GoodsImgEntity>(GoodsImgEntity.class));
				
		return resultList;
	}
	
	public List<GoodsImgEntity> seleteById(Integer goodsId){
		Assert.notNull(goodsId,"goodsId is null");
		String sql = " SELECT * FROM `rc_a_goods_img`  WHERE goods_id = :goodsId and img_type = :imgType ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("goodsId", goodsId);
		
		List<GoodsImgEntity> resultList = namedParameterJdbcTemplate
				.query(sql, paramMap, new BeanPropertyRowMapper<GoodsImgEntity>(GoodsImgEntity.class));
				
		return resultList;
	}
	
	public List<GoodsImgEntity> seleteByList(Integer pageNum,
			Integer pageSize){
		String sql = " SELECT * FROM rc_a_goods_img WHERE 1=1 limit :pageStartNum, :pageSize";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		List<GoodsImgEntity> resultList = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(GoodsImgEntity.class));
		return resultList;
	}
	
	public List<GoodsImgEntity> seleteByList(){
		String sql = " SELECT * FROM rc_a_goods_img WHERE 1=1 ";
		Map<String, Object> paramMap = Maps.newHashMap();
		List<GoodsImgEntity> resultList = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(GoodsImgEntity.class));
		return resultList;
	}
	
	public void saveGoodsImg(GoodsImgEntity goodsImgEntity){
		Assert.notNull(goodsImgEntity,"goodsImgEntity is null");
		String sql ="	INSERT INTO rc_a_goods_img	"
				+"	( goods_id, img_type, goods_img_ur)	"
				+ "	VALUES (  :goodsId, :imgType, :goodsImgUrl)	";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(goodsImgEntity));
	}
	
	/**
	 * 刪除
	 * @param goodsId
	 */
	public void deleteById(Integer goodsImgId){
		Assert.notNull(goodsImgId,"goodsImgId is null");
		String sql = "DELETE FROM `rc_a_goods_img` WHERE `goods_img_id`=:goodsImgId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("goodsImgId", goodsImgId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	public void update(GoodsImgEntity goodsImgEntity){
		Assert.notNull(goodsImgEntity,"goodsImgEntity is null");
		String sql = "	UPDATE `rc_a_goods_img`  "
				+ "	SET  `goodsImgUrl`=:goodsImgUrl	"
				+ " WHERE `goods_img_id`=:goodsImgId" ;
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("goodsImgId", goodsImgEntity.getGoodsImgId());
		paramMap.put("goodsImgUrl", goodsImgEntity.getGoodsImgUrl());
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

}
