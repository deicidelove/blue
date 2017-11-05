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
import com.common.system.entity.BlueDeptDoctorPic;
import com.google.common.collect.Maps;

/**
 * @author amkong
 *
 */
@Repository("deptDoctorPicDao")
public class DeptDoctorPicDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public BlueDeptDoctorPic findByDeptId(int deptId) {
		String sql ="SELECT * FROM tb_blue_deptdoctor_pic WHERE deptId =:deptId";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("deptId", deptId);
		
		List<BlueDeptDoctorPic> result =  namedParameterJdbcTemplate.query(
				sql.toString(), paramMap,
				new BeanPropertyRowMapper<BlueDeptDoctorPic>(BlueDeptDoctorPic.class));
		if(!CollectionUtils.isEmpty(result)){
			return result.get(0);
		}else{
			return null;
		}
	}
}
