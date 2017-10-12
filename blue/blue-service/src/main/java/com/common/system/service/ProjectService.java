/**
 * 
 */
package com.common.system.service;

import com.common.system.entity.BlueProject;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface ProjectService {

	public PageBean<BlueProject> findProjectList(int startPage, int limitLength);

	public Result<String> deleteProject(int sid);

	public Result<String> updateProject(String name, String context, int sid);

	public Result<String> addProject(String name, String context);

	public Result<BlueProject> findProject(int sid);

}
