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
import com.common.system.entity.BlueSource;
import com.common.system.entity.BlueStaff;
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("sourceDao")
public class SourceDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int findSourceListCount(int deptId, int positionId) {
		return positionId;
	}

	public List<BlueSource> findSourceList(int deptId, int positionId,
			int startRow, int limit) {
		return null;
	}

	public int findSourceByDeptCount(String date,int deptId) {
		StringBuilder sql =new StringBuilder(" SELECT COUNT(*) FROM tb_blue_source WHERE 1 = 1 ");
		Map<String, Object> paramMap = Maps.newHashMap();
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (deptId != -1) {
			sql.append(" AND dept_id =:deptId");
			paramMap.put("deptId", deptId);
		}
		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(), paramMap,
				Integer.class);
		return count;
	}

	public List<BlueSource> findSourceByDept(String date,int deptId, int startRow, int limit) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_source WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (deptId != -1) {
			sql.append(" AND dept_id =:deptId");
			paramMap.put("deptId", deptId);
		}
		sql.append(" LIMIT :startRow,:limit ");
		paramMap.put("startRow", startRow);
		paramMap.put("limit", limit);
		List<BlueSource> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueSource>(BlueSource.class));
		return result;
	}

	public int deleteSource(int sid) {
		String sql = "DELETE FROM  tb_blue_source  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int addSource(BlueSource source) {
		String sql = "INSERT INTO `tb_blue_source` (`title`, `url`, `dept_id`, `dept_name`, `position_id`, `context`, `create_time`)"
				+ " VALUES (:title,:url, :dept_id, :dept_name, :position_id, :context,:createTime)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("title", source.getTitle());
		paramMap.put("url", source.getUrl());
		paramMap.put("context", source.getContext());
		paramMap.put("dept_name", source.getDeptName());
		paramMap.put("dept_id", source.getDeptId());
		paramMap.put("position_id", source.getPositionId());
		paramMap.put("createTime", source.getCreateTime());
		int	 count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int updateSource(BlueSource source) {
		StringBuilder sql =new StringBuilder("UPDATE `tb_blue_source` SET title=:title, dept_id = :dept_id, dept_name = :dept_name,position_id =:position_id, context=:context ");
		Map<String, Object> paramMap = Maps.newHashMap();
		if(!StringUtils.isEmpty(source.getUrl())){
			sql.append(",url =:url");
			paramMap.put("url", source.getUrl());
		}
		sql.append(" WHERE sid =:sid");
		paramMap.put("sid", source.getSid());
		paramMap.put("title", source.getTitle());
		paramMap.put("context", source.getContext());
		paramMap.put("dept_name", source.getDeptName());
		paramMap.put("dept_id", source.getDeptId());
		paramMap.put("position_id", source.getPositionId());
		int	 count = namedParameterJdbcTemplate.update(sql.toString(), paramMap);
		return count;
	}

	public BlueSource findSource(int sid) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_source WHERE sid = :sid");
		paramMap.put("sid", sid);
		List<BlueSource> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueSource>(BlueSource.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}
}
