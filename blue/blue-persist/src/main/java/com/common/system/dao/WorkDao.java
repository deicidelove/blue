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

import com.common.system.entity.BlueNeedWork;
import com.common.system.entity.BlueStaff;
import com.common.system.entity.BlueWantWork;
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("workDao")
public class WorkDao {
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int findAllCount(String date) {

		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) FROM tb_blue_needWork WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}

		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(),
				paramMap, Integer.class);
		return count;
	}

	public int findwantCount(String date) {

		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) FROM tb_blue_wantwork WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}

		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(),
				paramMap, Integer.class);
		return count;
	}

	public List<BlueNeedWork> findAllWork(String date, int startPage,
			int limitLength) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM tb_blue_needWork WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		sql.append(" LIMIT :startRow,:limit ");
		paramMap.put("startRow", startPage);
		paramMap.put("limit", limitLength);
		List<BlueNeedWork> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueNeedWork>(BlueNeedWork.class));
		return result;
	}

	public List<BlueWantWork> findWantAllWork(String date, int startPage,
			int limitLength) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM tb_blue_wantwork WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		sql.append(" LIMIT :startRow,:limit ");
		paramMap.put("startRow", startPage);
		paramMap.put("limit", limitLength);
		List<BlueWantWork> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueWantWork>(BlueWantWork.class));
		return result;
	}

	public BlueNeedWork findBySid(int sid) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM tb_blue_needWork WHERE sid = :sid");
		paramMap.put("sid", sid);
		List<BlueNeedWork> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueNeedWork>(BlueNeedWork.class));
		if (!CollectionUtils.isEmpty(result)) {
			return result.get(0);
		} else {
			return null;
		}

	}

	public int deleteNeedWork(int sid) {

		String sql = "DELETE FROM  tb_blue_needWork  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int deleteWantWork(int sid) {

		String sql = "DELETE FROM  tb_blue_wantwork  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int updateWork(BlueNeedWork needWork) {
		String sql = "UPDATE tb_blue_needWork SET title=:title, needNum=:needNum, education=:education, experience=:experience, "
				+ "wages=:wages, work_time=:work_time, work_address=:work_address, description=:description, requirement=:requirement,fringe_benefits=:fringe_benefits WHERE sid=:sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", needWork.getSid());
		paramMap.put("title", needWork.getTitle());
		paramMap.put("needNum", needWork.getNeedNum());
		paramMap.put("education", needWork.getEducation());
		paramMap.put("experience", needWork.getEducation());
		paramMap.put("wages", needWork.getWages());
		paramMap.put("work_time", needWork.getWorkTime());
		paramMap.put("work_address", needWork.getWorkAddress());
		paramMap.put("description", needWork.getDescription());
		paramMap.put("requirement", needWork.getRequirement());
		paramMap.put("fringe_benefits", needWork.getFringeBenefits());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int addWork(BlueNeedWork needWork) {

		String sql = "INSERT INTO tb_blue_needWork ( `title`, `needNum`, `education`, `experience`, `wages`, `work_time`, `work_address`, `description`, `requirement`,`fringe_benefits`, `create_time`) "
				+ "VALUES (:title, :needNum, :education, :experience, :wages, :work_time, :work_address, :description, :requirement,:fringe_benefits, :createTime)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("title", needWork.getTitle());
		paramMap.put("needNum", needWork.getNeedNum());
		paramMap.put("education", needWork.getEducation());
		paramMap.put("experience", needWork.getExperience());
		paramMap.put("wages", needWork.getWages());
		paramMap.put("work_time", needWork.getWorkTime());
		paramMap.put("work_address", needWork.getWorkAddress());
		paramMap.put("description", needWork.getDescription());
		paramMap.put("requirement", needWork.getRequirement());
		paramMap.put("fringe_benefits", needWork.getFringeBenefits());
		paramMap.put("createTime", needWork.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int addWantWork(BlueWantWork wantWork) {

		String sql = "INSERT INTO tb_blue_wantwork (`name`, `phone`, `profession`, `want_job`, `want_wage`,`describe`, `create_time`) "
				+ "VALUES (:name, :phone, :profession, :want_job, :want_wage,:describe, :createTime)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("name", wantWork.getName());
		paramMap.put("phone", wantWork.getPhone());
		paramMap.put("profession", wantWork.getProfession());
		paramMap.put("want_job", wantWork.getWantJob());
		paramMap.put("want_wage", wantWork.getWantWage());
		paramMap.put("describe", wantWork.getDescribe());
		paramMap.put("createTime", wantWork.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
}
