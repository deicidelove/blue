/**
 * 
 */
package com.common.system.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.common.system.entity.BlueDoctorSchedule;
import com.google.common.collect.Maps;

/**
 * @author amkong
 * 
 */
@Repository("doctorScheduleDao")
public class DoctorScheduleDao {
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<BlueDoctorSchedule> findList(int start, int limit, String date,
			String deptId) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM tb_blue_doctor_schedule WHERE 1=1");
		if (!StringUtils.isEmpty(date)) {
			sql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = :date");
			paramMap.put("date", date);
		}
		if (!"-1".equals(deptId)) {
			sql.append(" AND dept_id = :deptId");
			paramMap.put("deptId", deptId);
		}

		sql.append(" LIMIT :startRow,:limit ");
		paramMap.put("startRow", start);
		paramMap.put("limit", limit);
		List<BlueDoctorSchedule> result = namedParameterJdbcTemplate.query(sql
				.toString(), paramMap,
				new BeanPropertyRowMapper<BlueDoctorSchedule>(
						BlueDoctorSchedule.class));
		return result;
	}

	public int findCount(String date, String deptId) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) FROM tb_blue_doctor_schedule WHERE 1=1");
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

	public int delete(int sid) {
		String sql = "DELETE FROM  tb_blue_doctor_schedule  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;

	}

	public int save(BlueDoctorSchedule blueDoctorSchedule) {
		String sql = "INSERT INTO `tb_blue_doctor_schedule` (`staff_id`,`dept_id`,`shift_time`,`dept_name`,`staff_name`,`count`,`create_time`)"
				+ " VALUES(:staff_id,:dept_id,:shift_time,:dept_name,:staff_name,:count,:create_time);";
		
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("staff_id", blueDoctorSchedule.getStaffId());
		paramMap.put("dept_id", blueDoctorSchedule.getDeptId());
		paramMap.put("shift_time", blueDoctorSchedule.getShiftTime());
		paramMap.put("dept_name", blueDoctorSchedule.getDeptName());
		paramMap.put("staff_name", blueDoctorSchedule.getStaffName());
		paramMap.put("count", blueDoctorSchedule.getCount());
		paramMap.put("create_time", blueDoctorSchedule.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
	
	public BlueDoctorSchedule findBySid(int sid){
		String sql ="SELECT * FROM tb_blue_doctor_schedule WHERE sid =:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		
		List<BlueDoctorSchedule> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueDoctorSchedule>(BlueDoctorSchedule.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}
	
	public int update(BlueDoctorSchedule blueDoctorSchedule){
		String sql = "UPDATE `tb_blue_doctor_schedule` "
				+ "SET shift_time=:shift_time,count=:count WHERE sid=:sid";
		
		Map<String, Object> paramMap = Maps.newHashMap();
//		paramMap.put("staff_id", blueDoctorSchedule.getStaffId());
//		paramMap.put("dept_id", blueDoctorSchedule.getDeptId());
		paramMap.put("shift_time", blueDoctorSchedule.getShiftTime());
//		paramMap.put("dept_name", blueDoctorSchedule.getDeptName());
//		paramMap.put("staff_name", blueDoctorSchedule.getStaffName());
		paramMap.put("count", blueDoctorSchedule.getCount());
		paramMap.put("sid", blueDoctorSchedule.getSid());
//		paramMap.put("create_time", blueDoctorSchedule.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
	
	
	public List<BlueDoctorSchedule> findSchedules(Integer staffId,String time,String startDate,String endDate){
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM tb_blue_doctor_schedule WHERE staff_id=:staff_id AND shift_time=:shift_time AND (create_time BETWEEN :startDate AND :endDate) ORDER BY create_time ASC") ;
		paramMap.put("staff_id", staffId);
		paramMap.put("shift_time", time);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		List<BlueDoctorSchedule> result = namedParameterJdbcTemplate.query(sql
				.toString(), paramMap,
				new BeanPropertyRowMapper<BlueDoctorSchedule>(
						BlueDoctorSchedule.class));
		return result;
	}

}
