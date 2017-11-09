/**
 * 
 */
package com.common.system.service;


import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueDept;
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
	public PageBean<BlueDept> findDepts(int start,int limit);

	public Result<Integer> deleteDept(int sid);

	public Result<BlueDept> findBySid(int sid);

	public Result<Integer> addDept(String name, String context,MultipartFile file);

	public Result<Integer> updateDept(String deptName,String context,int sid,MultipartFile file);

}
