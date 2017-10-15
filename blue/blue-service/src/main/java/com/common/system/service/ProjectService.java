/**
 * 
 */
package com.common.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueProject;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface ProjectService {

	public PageBean<BlueProject> findProjectList(String date,int startPage, int limitLength);

	public Result<Integer> deleteProject(int sid);

	public Result<Integer> updateProject(String name, String context, int sid,MultipartFile file);

	public Result<Integer> addProject(String name, String context,MultipartFile file);

	public Result<BlueProject> findProject(int sid);

}
