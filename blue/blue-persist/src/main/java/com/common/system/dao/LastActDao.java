package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.common.system.entity.LastActEntity;
import com.common.system.util.Convert;
import com.google.common.collect.Maps;

/**
 * 
 * @author Blackgun
 *	活動表增刪改查
 */
@Repository("lastActDao")
public class LastActDao {
	@Resource
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
	
	public LastActEntity seleteById(Integer lastActId){
		Assert.notNull(lastActId,"lastActId is null");
		String sql = " SELECT * FROM tb_blue_lastact WHERE sid = :lastActId ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("lastActId", lastActId);
		List<LastActEntity> resultList = namedParameterJdbcTemplate
				.query(sql, paramMap, new BeanPropertyRowMapper<LastActEntity>(LastActEntity.class));
		return !CollectionUtils.isEmpty(resultList) ? resultList.get(0): null;
	}
	
	public List<LastActEntity> seleteList(Integer pageNum, Integer pageSize){
		String sql = " SELECT * FROM tb_blue_lastact WHERE is_delete=0 limit :pageStartNum, :pageSize";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("pageStartNum", (pageNum-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		List<LastActEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(LastActEntity.class));
		return result;
	}
	
	public List<LastActEntity> seleteList(){
		String sql = " SELECT * FROM tb_blue_lastact WHERE is_delete=0 order by create_time desc";
		Map<String, Object> paramMap = Maps.newHashMap();
		List<LastActEntity> result = namedParameterJdbcTemplate.query(sql, 
				paramMap, BeanPropertyRowMapper.newInstance(LastActEntity.class));
		return result;
	}
	
	public int save(LastActEntity lastActEntity){
		Assert.notNull(lastActEntity,"lastActEntity is null");
		String sql = "INSERT INTO  `tb_blue_lastact` ( `last_act_name`, `last_act_enname`,  `last_act_title`, `last_act_content`, `last_act_list_img`) "
				+ " VALUES "
				+ "( :lastActName,:lastActEnname, :lastActTitle, :lastActContent, :lastActListImg ) ";
		return namedParameterJdbcTemplate.update(sql, Convert.beanToMap(lastActEntity));
	}
	
	/**
	 * 刪除并非真正刪除
	 * @param actId
	 */
	public void deleteById(Integer lastActId){
		Assert.notNull(lastActId,"lastActId is null");
		String sql = "UPDATE `tb_blue_lastact` SET is_delete=1 WHERE (`sid`=:lastActId)";
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("lastActId", lastActId);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	
	public void update(LastActEntity lastActEntity){
		Assert.notNull(lastActEntity,"lastActEntity is null");
		String sql = "UPDATE `tb_blue_lastact` "
				+ "SET `last_act_name`=:lastActName, `last_act_title`=:lastActTitle, "
				+ "`last_act_content`=:lastActContent, `last_act_list_img`=:lastActListImg "
				+ "  WHERE `sid`=:sid";
		
		namedParameterJdbcTemplate.update(sql, Convert.beanToMap(lastActEntity));
	}

}
