/**
 * 
 */
package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.common.system.entity.BluePation;
import com.common.system.entity.BlueProject;
import com.common.system.entity.BlueShift;
import com.google.common.collect.Maps;

/**
 * @author amkong
 *
 */
@Repository("pationDao")
public class PationDao {
	
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public List<BluePation> findPationsByuserId(String userId){
		String sql = "SELECT * FROM tb_blue_pation WHERE user_id=:user_id";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("user_id", userId);
		List<BluePation> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BluePation>(BluePation.class));
		return result;
	}
	
	public BluePation findPationBysid(Integer sid){
		String sql = "SELECT * FROM tb_blue_pation WHERE sid=:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		List<BluePation> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BluePation>(BluePation.class));
		if(CollectionUtils.isEmpty(result)){
			return new BluePation();
		}
		return result.get(0);
	}
	
	public Integer updateIsDefault(Integer sid, Integer isDefault){
		
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_pation` SET is_default =:is_default WHERE sid = :sid");
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		paramMap.put("is_default", isDefault);
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}
	
	public Integer updateIsDefaultByUserId(String userId, Integer isDefault){
		
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_pation` SET is_default =:is_default WHERE user_id = :user_id");
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("user_id", userId);
		paramMap.put("is_default", isDefault);
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}
	
	public int addProject(BluePation pation) {
		String sql = "INSERT INTO `tb_blue_pation` ( `name`, `phone`, `user_id`,`is_default`, `create_time`) "
				+ "VALUES (:name, :phone, :user_id, :is_default, :create_time)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("phone", pation.getPhone());
		paramMap.put("name", pation.getName()==null?"":pation.getName());
		paramMap.put("user_id", pation.getUserId());
		paramMap.put("is_default", pation.getIsDefault());
		paramMap.put("create_time", pation.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
	
	public int updateProject(BluePation pation) {
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_pation` SET name =:name,phone =:phone,is_default=:is_default WHERE sid = :sid ");
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", pation.getSid());
		paramMap.put("name", pation.getName());
		paramMap.put("phone", pation.getPhone());
		paramMap.put("is_default", pation.getIsDefault());
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}
	
	public int deletePation(int sid) {
		String sql = "DELETE FROM  tb_blue_pation  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
	
}
