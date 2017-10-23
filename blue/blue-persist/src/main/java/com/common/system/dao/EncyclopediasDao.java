package com.common.system.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.common.system.entity.BlueEncyclopedias;
import com.google.common.collect.Maps;
@Repository("encyclopediasDao")
public class EncyclopediasDao {
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<BlueEncyclopedias> findList(int type,String date, int startRow, int limit) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_encyclopedias WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (type != -1) {
			sql.append(" AND type =:type");
			paramMap.put("type", type);
		}
		sql.append(" LIMIT :startRow,:limit ");
		paramMap.put("startRow", startRow);
		paramMap.put("limit", limit);
		List<BlueEncyclopedias> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueEncyclopedias>(BlueEncyclopedias.class));
		return result;
	}

	public int findNum(int type,String date) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tb_blue_encyclopedias WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (type != -1) {
			sql.append(" AND type =:type");
			paramMap.put("type", type);
		}
		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(), paramMap,
				Integer.class);
		return count;
	}

	public int deleteNum(int sid) {
		String sql = "DELETE FROM  tb_blue_encyclopedias  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int addencyclopedias(BlueEncyclopedias encyclopedias) {
		
		String sql = "INSERT INTO `tb_blue_encyclopedias` ( `type`, `url`, `title`, `context`, `create_time`) "
				+ "VALUES (:type, :url, :title, :context, :create_time)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("type", encyclopedias.getType());
		paramMap.put("url", encyclopedias.getUrl());
		paramMap.put("title", encyclopedias.getTitle());
		paramMap.put("context", encyclopedias.getContext());
		paramMap.put("create_time", encyclopedias.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public BlueEncyclopedias findencyclopedias(int sid) {
		String sql ="SELECT * FROM tb_blue_encyclopedias WHERE sid =:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		
		List<BlueEncyclopedias> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueEncyclopedias>(BlueEncyclopedias.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}

	public int updateencyclopedias(BlueEncyclopedias encyclopedias) {
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_encyclopedias` SET type =:type,title =:title,context =:context");
		Map<String, Object> paramMap = Maps.newHashMap();
		if(!StringUtils.isEmpty(encyclopedias.getUrl())){
			sql.append(",url =:url");
			paramMap.put("url", encyclopedias.getUrl());
		}
		sql.append(" WHERE sid =:sid");
		paramMap.put("sid", encyclopedias.getSid());
		paramMap.put("type", encyclopedias.getType());
		paramMap.put("title", encyclopedias.getTitle());
		paramMap.put("context", encyclopedias.getContext());
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}
	
	public int updateUrl(BlueEncyclopedias encyclopedias) {
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_encyclopedias` SET url:=url ");
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("url", encyclopedias.getUrl());
		sql.append(" WHERE sid =:sid");
		paramMap.put("sid", encyclopedias.getSid());
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}
}

