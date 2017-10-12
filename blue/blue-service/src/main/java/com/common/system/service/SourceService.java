/**
 * 
 */
package com.common.system.service;

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

	public PageBean<BlueSource> findSourceByDept(int deptId, int startPage,
			int limit);

	public Result<String> deleteSource(int sid);

	public Result<String> addSource(int deptId, int positionId, String context,
			String title);

	public Result<String> updateSource(int sid, int deptId, int positionId,
			String context, String title);

	public Result<BlueSource> findSource(int sid);
}
