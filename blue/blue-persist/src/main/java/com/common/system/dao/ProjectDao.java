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
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("projectDao")
public class ProjectDao {
	
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int findCount(String date) {
		
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tb_blue_project WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(), paramMap,
				Integer.class);
		return count;
	}

	public List<BlueProject> findProjectList(String date,int startRow, int limitLength) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_project WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		sql.append(" LIMIT :startRow,:limit ");
		paramMap.put("startRow", startRow);
		paramMap.put("limit", limitLength);
		List<BlueProject> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueProject>(BlueProject.class));
		return result;
	}

	public int deleteProject(int sid) {
		String sql = "DELETE FROM  tb_blue_project  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int addProject(BlueProject project) {
		String sql = "INSERT INTO `tb_blue_project` ( `url`, `name`, `context`, `create_time`) "
				+ "VALUES (:url, :name, :context, :create_time)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("url", project.getUrl()==null?"":project.getUrl());
		paramMap.put("name", project.getName()==null?"":project.getName());
		paramMap.put("context", project.getContext()==null?"":project.getContext());
		paramMap.put("create_time", project.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int updateProject(BlueProject project) {
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_project` SET name =:name,context =:context");
		Map<String, Object> paramMap = Maps.newHashMap();
		if(!StringUtils.isEmpty(project.getUrl())){
			sql.append(",url =:url");
			paramMap.put("url", project.getUrl());
		}
				sql.append(" WHERE sid =:sid");
		paramMap.put("sid", project.getSid());
		paramMap.put("name", project.getName());
		paramMap.put("context", project.getContext());
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}

	public BlueProject findProject(int sid) {
		String sql ="SELECT * FROM tb_blue_project WHERE sid =:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		
		List<BlueProject> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueProject>(BlueProject.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}

}
