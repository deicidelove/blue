/**
 * 
 */
package com.common.system.service;

import com.common.system.entity.BlueStaff;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface StaffService {

	public PageBean<BlueStaff> findStaff(int startPage, int limitLength,
			String name, int deptId, String diseaseNmae);

	public Result<BlueStaff> findBySid(int sid);

	public Result<String> deleteStaff(int sid);

	public Result<String> updateStaff(String info);

	public Result<String> addStaff(String info);

}
