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

import com.common.system.entity.RcDept;
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("deptDao")
public class DeptDao {
	
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public int findCount(){
		String sql = " SELECT COUNT(*) FROM rc_dept  ";
		Map<String, Object> paramMap = Maps.newHashMap();

		int count = namedParameterJdbcTemplate.queryForObject(sql,paramMap,
				Integer.class);

		return count;
		
		
	}

	public List<RcDept> findDepts(int startRow,int limit) {
		String sql = " SELECT * FROM rc_dept  LIMIT :startRow,:limit ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("startRow", startRow);
		paramMap.put("limit", limit);
		List<RcDept> result = namedParameterJdbcTemplate.query(sql,
				paramMap, new BeanPropertyRowMapper<RcDept>(
						RcDept.class));
		return result;
	}

	public int deleteDept(int sid) {
		return sid;
	}

	public int addDept(RcDept dept) {
		return 0;
	}

	public RcDept findBySid(int sid) {
		return null;
	}

	public int updateDept(RcDept dept) {
		return 0;
	}

}
