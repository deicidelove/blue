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
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueDeptDoctorPic;
import com.common.system.entity.BlueStaff;
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("deptDoctorPicDao")
public class DeptDoctorPicDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public BlueDeptDoctorPic findByDeptId(int deptId) {
		String sql = "SELECT * FROM tb_blue_deptdoctor_pic WHERE deptId =:deptId";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("deptId", deptId);

		List<BlueDeptDoctorPic> result = namedParameterJdbcTemplate.query(sql
				.toString(), paramMap,
				new BeanPropertyRowMapper<BlueDeptDoctorPic>(
						BlueDeptDoctorPic.class));
		if (!CollectionUtils.isEmpty(result)) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public int findAllCount(String date, String deptId) {

		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) FROM tb_blue_deptdoctor_pic WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (!"-1".equals(deptId)) {
			sql.append(" AND dept_id = :deptId");
			paramMap.put("deptId", deptId);
		}
		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(),
				paramMap, Integer.class);
		return count;
	}

	public List<BlueDeptDoctorPic> findAllDeptDoctorPic(String date,
			String deptId, int startPage, int limitLength) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM tb_blue_deptdoctor_pic WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (!"-1".equals(deptId)) {
			sql.append(" AND dept_id = :deptId");
			paramMap.put("deptId", deptId);
		}
		sql.append(" LIMIT :startRow,:limit ");
		paramMap.put("startRow", startPage);
		paramMap.put("limit", limitLength);
		List<BlueDeptDoctorPic> result = namedParameterJdbcTemplate.query(sql
				.toString(), paramMap,
				new BeanPropertyRowMapper<BlueDeptDoctorPic>(
						BlueDeptDoctorPic.class));
		return result;
	}

	public int delete(int sid) {

		String sql = "DELETE FROM  tb_blue_deptdoctor_pic  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int add(BlueDeptDoctorPic pic) {
		String sql = "INSERT INTO `tb_blue_deptdoctor_pic` (`url`, `deptId`,`deptName`) "
				+ "VALUES (:url, :deptId, :deptName)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("url", pic.getUrl());
		paramMap.put("deptId", pic.getDeptId());
		paramMap.put("deptName", pic.getDeptName());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

}
