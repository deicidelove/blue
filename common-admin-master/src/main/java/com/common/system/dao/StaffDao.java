/**
 * 
 */
package com.common.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.system.entity.BlueStaff;

/**
 * @author amkong
 * 
 */
@Repository("staffDao")
public class StaffDao {

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
		return null;
	}

	public int deleteStaff(int sid) {
		return sid;
	}

	public int updateStaff(BlueStaff staff) {
		return 0;
	}

	public int addStaff(BlueStaff staff) {
		return 0;
	}

}
