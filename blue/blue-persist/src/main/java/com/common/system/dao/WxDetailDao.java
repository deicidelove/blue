package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.common.system.entity.WxDetailEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	微信详情表增刪改查
 */
@Repository("wxDetailDao")
public class WxDetailDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public WxDetailEntity seleteById( String openId){
		Assert.notNull(openId,"openId is null");
		String sql = " SELECT * FROM `rc_a_wx_detail`  WHERE open_id = :openId  ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("openId", openId);
		
		List<WxDetailEntity> resultList = namedParameterJdbcTemplate
				.query(sql, paramMap, new BeanPropertyRowMapper<WxDetailEntity>(WxDetailEntity.class));
				
		return CollectionUtils.isEmpty(resultList)?null: resultList.get(0);
	}
	
	public List<WxDetailEntity> seleteByList(Integer pageNum,
			Integer pageSize){
		String sql = " SELECT * FROM rc_a_wx_detail WHERE 1=1 limit :pageStartNum, :pageSize";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		List<WxDetailEntity> resultList = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(WxDetailEntity.class));
		return resultList;
	}
	
	public List<WxDetailEntity> seleteByList(){
		String sql = " SELECT * FROM rc_a_wx_detail WHERE 1=1 ";
		Map<String, Object> paramMap = Maps.newHashMap();
		List<WxDetailEntity> resultList = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(WxDetailEntity.class));
		return resultList;
	}
	
	public void saveWxDetailEntity(WxDetailEntity wxDetailEntity){
		Assert.notNull(wxDetailEntity,"wxDetailEntity is null");
		String sql ="	INSERT INTO rc_a_wx_detail	"
				+"	( open_id, sex, name, pic)	"
				+ "	VALUES (  :openId, :sex, :name, :pic)	";
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(wxDetailEntity));
	}
	
	/**
	 * 刪除
	 * @param goodsId
	 */
	public void deleteById(String openId){
		Assert.notNull(openId,"openId is null");
		String sql = "DELETE FROM `rc_a_wx_detail` WHERE `open_id`=:openId";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("openId", openId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	public void update(WxDetailEntity WxDetailEntity){
		Assert.notNull(WxDetailEntity,"WxDetailEntity is null");
		String sql = "	UPDATE `rc_a_wx_detail`  "
				+ "	SET  `pic`=:pic	"
				+ " WHERE `open_id`=:openId" ;
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("openId", WxDetailEntity.getOpenId());
		paramMap.put("pic", WxDetailEntity.getPic());
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

}
