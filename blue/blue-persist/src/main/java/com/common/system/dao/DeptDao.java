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

import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueDoctorSchedule;
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
		String sql = " SELECT COUNT(*) FROM tb_blue_dept  ";
		Map<String, Object> paramMap = Maps.newHashMap();

		int count = namedParameterJdbcTemplate.queryForObject(sql,paramMap,
				Integer.class);

		return count;
		
		
	}

	public List<BlueDept> findDepts(int startRow,int limit) {
		String sql = " SELECT * FROM tb_blue_dept  LIMIT :startRow,:limit ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("startRow", startRow);
		paramMap.put("limit", limit);
		List<BlueDept> result = namedParameterJdbcTemplate.query(sql,
				paramMap, new BeanPropertyRowMapper<BlueDept>(
						BlueDept.class));
		return result;
	}

	public int deleteDept(int sid) {
		String sql = "DELETE FROM  tb_blue_dept  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int addDept(BlueDept dept) {
		String sql = "INSERT INTO `tb_blue_dept` (`name`,`context`,`create_time`) "
				+ "VALUES(:name,:context,:create_time)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("name", dept.getName());
		paramMap.put("context", dept.getContext());
		paramMap.put("create_time", dept.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public BlueDept findBySid(int sid) {
		String sql ="SELECT * FROM tb_blue_dept WHERE sid =:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		
		List<BlueDept> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueDept>(BlueDept.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}

	public int updateDept(BlueDept dept) {
		String sql = "UPDATE `tb_blue_dept` SET name=:name,context=:context WHERE sid=:sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("name", dept.getName());
		paramMap.put("context", dept.getContext());
		paramMap.put("sid", dept.getSid());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

}
