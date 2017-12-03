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

import com.common.system.entity.BlueDoctorSchedule;
import com.common.system.entity.BlueOppointment;
import com.google.common.collect.Maps;

/**
 * @author amkong
 *
 */
@Repository("oppointmentDao")
public class OppointmentDao {
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<BlueOppointment> findList(int start, int limit, String date,
			String deptId) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM tb_blue_oppointment WHERE 1=1");
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
		List<BlueOppointment> result = namedParameterJdbcTemplate.query(sql
				.toString(), paramMap,
				new BeanPropertyRowMapper<BlueOppointment>(
						BlueOppointment.class));
		return result;
	}

	public int findCount(String date, String deptId) {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) FROM tb_blue_oppointment WHERE 1=1");
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
		String sql = "DELETE FROM  tb_blue_oppointment  WHERE sid = :sid ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;

	}

	public int save(BlueOppointment blueOppointment) {
		String sql = "INSERT INTO `tb_blue_oppointment` (`staff_id`,`staff_name`,`dept_id`,`dept_name`,`pation_id`,`user_id`,`pay_money`,`context`,`order_time`,`create_time`)"
				+ " VALUES(:staff_id,:staff_name,:dept_id,:dept_name,:pation_id,:user_id,:pay_money,:context,:order_time,:create_time);";
		
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("staff_id", blueOppointment.getStaffId());
		paramMap.put("dept_id", blueOppointment.getDeptId());
		paramMap.put("staff_name", blueOppointment.getStaffName());
		paramMap.put("dept_name", blueOppointment.getDeptName());
		paramMap.put("pation_id", blueOppointment.getPationId());
		paramMap.put("user_id", blueOppointment.getUserId());
		paramMap.put("pay_money", blueOppointment.getPayMoney());
		paramMap.put("context", blueOppointment.getContext()==null?"":blueOppointment.getContext());
		paramMap.put("order_time", blueOppointment.getOrderTime());
		paramMap.put("create_time", blueOppointment.getCreateTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
	
	public BlueOppointment findBySid(int sid){
		String sql ="SELECT * FROM tb_blue_oppointment WHERE sid =:sid";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", sid);
		
		List<BlueOppointment> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueOppointment>(BlueOppointment.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}
	
	public BlueOppointment find(Integer staffId,Integer pationId, String userId, String orderTime){
		String sql ="SELECT * FROM tb_blue_oppointment WHERE staff_id =:staffId and pation_id=:pationId"
				+ " and user_id=:userId and order_time=:orderTime ";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("staffId", staffId);
		paramMap.put("pationId", pationId);
		paramMap.put("userId", userId);
		paramMap.put("orderTime", orderTime);
		List<BlueOppointment> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueOppointment>(BlueOppointment.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}
	
	public int update(BlueOppointment blueOppointment){
		String sql = "UPDATE `tb_blue_oppointment` "
				+ "SET order_time=:order_time WHERE sid=:sid";
		
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("sid", blueOppointment.getSid());
		paramMap.put("order_time", blueOppointment.getOrderTime());
		int count = namedParameterJdbcTemplate.update(sql, paramMap);
		return count;
	}
	
	public List<BlueOppointment> findByUserId(String userId){
		String sql ="SELECT * FROM tb_blue_oppointment WHERE user_id =:user_id";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("user_id", userId);
		
		List<BlueOppointment> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueOppointment>(BlueOppointment.class));
		return result;
	}

}
