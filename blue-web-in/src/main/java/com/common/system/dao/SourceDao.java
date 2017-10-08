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

import com.common.system.entity.BlueSource;
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

	public int findSourceByDeptCount(int deptId) {
		String sql = " SELECT COUNT(*) FROM tb_blue_source WHERE dept_id = :deptId ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("deptId", deptId);

		int count = namedParameterJdbcTemplate.queryForObject(sql, paramMap,
				Integer.class);

		return count;
	}

	public List<BlueSource> findSourceByDept(int deptId, int startRow, int limit) {
		String sql = " SELECT * FROM tb_blue_source WHERE dept_id = :deptId LIMIT :startRow,:limit ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("deptId", deptId);
		paramMap.put("startRow", startRow);
		paramMap.put("limit", limit);
		List<BlueSource> result = namedParameterJdbcTemplate.query(sql,
				paramMap, new BeanPropertyRowMapper<BlueSource>(
						BlueSource.class));
		return result;
	}

	public int deleteSource(int sid) {
		return sid;
	}

	public int addSource(BlueSource source) {
		return 0;
	}

	public int updateSource(BlueSource source) {
		return 0;
	}

	public BlueSource findSource(int sid) {
		return null;
	}
}
