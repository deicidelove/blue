/**
 * 
 */
package com.common.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueSource;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface SourceService {
	public PageBean<BlueSource> findSourceList(int deptId, String jobNum,
			String password, int startPage, int limit);

	public PageBean<BlueSource> findSourceByDept(String date,int deptId, int startPage,
			int limit);

	public Result<Integer> deleteSource(int sid);

	public Result<Integer> addSource(int deptId, int positionId, String context,
			String title,MultipartFile file);

	public Result<Integer> updateSource(int sid, int deptId, int positionId,
			String context, String title,MultipartFile file);

	public Result<BlueSource> findSource(int sid);
}
