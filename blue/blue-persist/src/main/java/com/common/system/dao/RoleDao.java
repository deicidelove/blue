/**
 * 
 */
package com.common.system.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.common.system.entity.BlueDoctorSchedule;
import com.common.system.entity.RcRole;

/**
 * @author amkong
 *
 */
@Repository("roleDao")
public class RoleDao {
	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public RcRole findDoctorId(){
		
		String sql = "SELECT * FROM rc_role WHERE value ='doctor' ";
		
		List<RcRole> result =  namedParameterJdbcTemplate.query(
				sql.toString(),
				new BeanPropertyRowMapper<RcRole>(RcRole.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
		
	}

}
