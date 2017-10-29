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

import com.common.system.entity.BlueStaff;
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("staffDao")
public class StaffDao {
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public int findAllCount(String date, String deptId){
		
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tb_blue_staff WHERE 1=1 ");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (!"-1".equals(deptId)) {
			sql.append(" AND dept_id = :deptId");
			paramMap.put("deptId", deptId);
		}
		int count = namedParameterJdbcTemplate.queryForObject(sql.toString(), paramMap,
				Integer.class);
		return count;
	}
	
	public List<BlueStaff> findAllStaff(String date, String deptId,int startPage, int limitLength){
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_staff WHERE 1=1 ");
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
		List<BlueStaff> result = namedParameterJdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<BlueStaff>(BlueStaff.class));
		return result;
	}

	public BlueStaff findByJobNum(String jobNum, String password) {
		return null;
	}

	public int findCount(String name, int deptId, String diseaseNmae) {
		return deptId;
	}

	public List<BlueStaff> findStaff(int startPage, int limitLength,
			String name, int deptId, String diseaseNmae) {
		return null;
	}

	public BlueStaff findBySid(int sid) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_staff WHERE sid = :sid");
		paramMap.put("sid", sid);
		List<BlueStaff> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueStaff>(BlueStaff.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
		
	}

	public int deleteStaff(int sid) {
		
		String sql = "DELETE FROM  tb_blue_staff  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int updateStaff(BlueStaff staff) {
		String sql = "UPDATE tb_blue_staff SET name =:name,dept_id =:deptId,sex =:sex,phone = :phone,position_id = :level,introduce = :introduce,address =:address WHERE sid =:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("name", staff.getName());
		paramMap.put("deptId", staff.getDeptId());
		paramMap.put("sex", staff.getSex());
		paramMap.put("phone", staff.getPhone());
		paramMap.put("level", staff.getPositionId());
		paramMap.put("sid", staff.getSid());
		paramMap.put("address", staff.getAddress());
		paramMap.put("introduce", staff.getIntroduce());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}

	public int addStaff(BlueStaff staff) {
		
	String sql = "INSERT INTO tb_blue_staff (`name`, `password`, `job_num`, `dept_name`, `dept_id`, `sex`, `phone`, `introduce`, `position_id`,`address`, `create_time`) "
				+ "VALUES (:name, :password, :jobNum, :deptName, :deptId, :sex, :phone, :introduce, :positionId,:address :createTime)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("name", staff.getName());
		paramMap.put("password", staff.getPassword());
		paramMap.put("jobNum", staff.getJobNum());
		paramMap.put("deptName", staff.getDeptName());
		paramMap.put("deptId", staff.getDeptId());
		paramMap.put("sex", staff.getSex());
		paramMap.put("phone", staff.getPhone());
		paramMap.put("positionId", staff.getPositionId());
		paramMap.put("introduce", staff.getIntroduce());
		paramMap.put("address", staff.getAddress());
		paramMap.put("createTime", staff.getCreateTime());
		int	 count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
	
	
	public List<BlueStaff> findLikeBySearch(String search){
		search = "%" + search +"%";
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_blue_staff WHERE introduce LIKE :search OR name LIKE :search ");
		paramMap.put("search", search);
		List<BlueStaff> result = namedParameterJdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<BlueStaff>(BlueStaff.class));
		return result;
		
		
		
	}

}
