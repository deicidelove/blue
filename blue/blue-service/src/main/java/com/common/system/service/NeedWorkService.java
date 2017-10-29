/**
 * 
 */
package com.common.system.service;

import com.common.system.entity.BlueNeedWork;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
public interface NeedWorkService {
	
	public Result<BlueNeedWork> findBySid(int sid);
	
	public Result<Integer> saveAddWork(String addName, String phone, String educattion,String wantJob,
			String wageWant,String describe);

}
