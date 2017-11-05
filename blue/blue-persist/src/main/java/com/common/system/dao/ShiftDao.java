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

import com.common.system.entity.BlueShift;
import com.google.common.collect.Maps;

/**
 * @author amkong
 *
 */
@Repository("shiftDao")
public class ShiftDao {
	
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<BlueShift> findShiftsByscheduleId(Integer scheduleId){
		String sql = "SELECT * FROM tb_blue_shift WHERE schedule_id=:schedule_id";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("schedule_id", scheduleId);
		List<BlueShift> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueShift>(BlueShift.class));
		return result;
	}
	
	public BlueShift findShif(Integer sid){
		String sql = "SELECT * FROM tb_blue_shift WHERE sid=:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		List<BlueShift> result = namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueShift>(BlueShift.class));
		if(CollectionUtils.isEmpty(result)){
			return null;
		}
		return result.get(0);
	}
	
	public int insert(BlueShift shift){
		String sql = "INSERT INTO `tb_blue_shift` ( `schedule_id`, `time`, `count`, `create_time`) "
				+ "VALUES (:schedule_id, :time, :count, :create_time)";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("schedule_id", shift.getScheduleId());
		paramMap.put("time", shift.getTime());
		paramMap.put("count", shift.getCount());
		paramMap.put("create_time", shift.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
	
	public int deleteNum(int schedule_id) {
		String sql = "DELETE FROM  tb_blue_shift  WHERE schedule_id = :schedule_id ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("schedule_id", schedule_id);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
}
