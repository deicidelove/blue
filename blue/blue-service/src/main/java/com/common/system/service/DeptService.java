/**
 * 
 */
package com.common.system.service;

import java.util.List;

import com.common.system.entity.RcDept;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface DeptService {

	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	public PageBean<RcDept> findDepts(int start,int limit);

	public Result<String> deleteDept(int sid);

	public Result<RcDept> findBySid(int sid);

	public Result<String> addDept(String name, String context);

	public Result<String> updateDept(String name, String context, int sid);

}
