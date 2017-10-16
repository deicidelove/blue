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
import com.common.system.entity.BlueHospital;
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("hospitalDao")
public class HospitalDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int findCount(String date,int type) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tb_blue_hospital WHERE 1=1 ");
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

	public List<BlueHospital> findBlueHospitals(String date,int type, int startRow,
			int limitLength) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_hospital WHERE 1=1 ");
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
		paramMap.put("limit", limitLength);
		List<BlueHospital> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueHospital>(BlueHospital.class));
		return result;
	}

	public int deleteHospital(int sid) {
		String sql = "DELETE FROM  tb_blue_hospital  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public BlueHospital findHospital(int sid) {
		String sql ="SELECT * FROM tb_blue_hospital WHERE sid =:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		
		List<BlueHospital> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueHospital>(BlueHospital.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}

	public int updateHospital(BlueHospital hospital) {
		StringBuilder sql = new StringBuilder("UPDATE `tb_blue_hospital` SET type =:type,title =:title,context =:context");
		Map<String, Object> paramMap = Maps.newHashMap();
		if(!StringUtils.isEmpty(hospital.getUrl())){
			sql.append(",url =:url");
			paramMap.put("url", hospital.getUrl());
		}
		sql.append(" WHERE sid =:sid");
		paramMap.put("sid", hospital.getSid());
		paramMap.put("type", hospital.getType());
		paramMap.put("title", hospital.getTitle());
		paramMap.put("context", hospital.getContext());
		int count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}

	public int addHospital(BlueHospital hospital) {
		String sql = "INSERT INTO `tb_blue_hospital` ( `type`, `url`, `title`, `context`, `create_time`) "
				+ "VALUES (:type, :url, :title, :context, :create_time)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("type", hospital.getType());
		paramMap.put("url", hospital.getUrl());
		paramMap.put("title", hospital.getTitle());
		paramMap.put("context", hospital.getContext());
		paramMap.put("create_time", hospital.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

}
