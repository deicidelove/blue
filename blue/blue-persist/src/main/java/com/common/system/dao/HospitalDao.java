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

	public int findCount(int type) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tb_blue_hospital ");
		if (type != -1) {
			sql.append("WHERE type =:type");
			paramMap.put("type", type);
		}
		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(), paramMap,
				Integer.class);

		return count;
	}

	public List<BlueHospital> findBlueHospitals(int type, int startRow,
			int limitLength) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_hospital ");
		if (type != -1) {
			sql.append("WHERE type =:type");
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
		return sid;
	}

	public BlueHospital findHospital(int sid) {
		return null;
	}

	public int updateHospital(BlueHospital hospital) {
		return 0;
	}

	public int addHospital(BlueHospital hospital) {
		return 0;
	}

}
