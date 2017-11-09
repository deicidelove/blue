/**
 * 
 */
package com.common.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueProject;
import com.common.system.entity.BlueProjectAdvert;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
public interface ProjectAdvertService {
	
	public PageBean<BlueProjectAdvert> findProjectAdevertList(String date,int startPage, int limitLength);

	public Result<Integer> deleteProjectAdevert(int sid);

	public Result<Integer> updateProjectAdevert(String title, String context,String jumpUrl,
			int sid, MultipartFile file);

	public Result<Integer> addProjectAdevert(String title, String context,String jumpUrl,
			 MultipartFile file);

	public Result<BlueProjectAdvert> findProjectAdevert(int sid);

}
