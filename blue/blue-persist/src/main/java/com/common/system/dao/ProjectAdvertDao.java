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

import com.common.system.entity.BlueAdvert;
import com.common.system.entity.BlueProject;
import com.common.system.entity.BlueProjectAdvert;
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("projectAdvertDao")
public class ProjectAdvertDao {
	
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int findCount(String date,int type) {
		
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tb_blue_projectAdvert WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if(type != -1){
			sql.append(" AND type = :type");
			paramMap.put("type", type);
		}
		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(), paramMap,
				Integer.class);
		return count;
	}

	public List<BlueProjectAdvert> findProjectList(String date,int startRow, int limitLength,int type) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_projectAdvert WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if(type != -1){
			sql.append(" AND type = :type ");
			paramMap.put("type", type);
		}
		sql.append(" LIMIT :startRow,:limit ");
		paramMap.put("startRow", startRow);
		paramMap.put("limit", limitLength);
		List<BlueProjectAdvert> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueProjectAdvert>(BlueProjectAdvert.class));
		return result;
	}

	public int deleteProjectAdvert(int sid) {
		String sql = "DELETE FROM  tb_blue_projectAdvert  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int addProjectAdvert(BlueProjectAdvert projectAdvert) {
		String sql = "INSERT INTO `tb_blue_projectAdvert` ( `title`, `context`, `pic_url`, `jump_url`,`type`, `create_time`) "
				+ "VALUES (:title, :context, :pic_url,:jump_url,:type, :create_time)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("title", projectAdvert.getTitle()==null?"":projectAdvert.getTitle());
		paramMap.put("context", projectAdvert.getContext()==null?"":projectAdvert.getContext());
		paramMap.put("pic_url", projectAdvert.getPicUrl()==null?"":projectAdvert.getPicUrl());
		paramMap.put("jump_url", projectAdvert.getJumpUrl()==null?"":projectAdvert.getJumpUrl());
		paramMap.put("type", projectAdvert.getType());
		paramMap.put("create_time", projectAdvert.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int updateProjectAdvert(BlueProjectAdvert projectAdvert) {
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_projectAdvert` SET title =:title,context =:context,jump_url=:jump_url,type=:type ");
	
		Map<String, Object> paramMap = Maps.newHashMap();
		if(!StringUtils.isEmpty(projectAdvert.getPicUrl())){
			sql.append(",pic_url =:pic_url");
			paramMap.put("pic_url", projectAdvert.getPicUrl());
		}
				sql.append(" WHERE sid =:sid");
		paramMap.put("title", projectAdvert.getTitle());
		paramMap.put("jump_url", projectAdvert.getJumpUrl());
		paramMap.put("context", projectAdvert.getContext());
		paramMap.put("type", projectAdvert.getType());
		paramMap.put("sid", projectAdvert.getSid());
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}

	public BlueProjectAdvert findProjectAdvert(int sid) {
		String sql ="SELECT * FROM tb_blue_projectAdvert WHERE sid =:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		
		List<BlueProjectAdvert> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueProjectAdvert>(BlueProjectAdvert.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}
	
	public List<BlueProjectAdvert> findTypeAdvert(int type) {
		String sql ="SELECT * FROM tb_blue_projectAdvert WHERE type =:type";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("type", type);
		
		List<BlueProjectAdvert> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueProjectAdvert>(BlueProjectAdvert.class));
		return result;
	}

}
