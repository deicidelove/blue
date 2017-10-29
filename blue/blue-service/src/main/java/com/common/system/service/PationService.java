/**
 * 
 */
package com.common.system.service;


import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
public interface PationService {
	
	public Result<Integer> updatePationIsDefault(Integer sid,Integer isDefault);
	
	public Result<Integer> deletePation(int sid);

	public Result<Integer> updatePation(String name, String phone, int sid,Integer isDefault);

	public Result<Integer> addPation(String name, String phone,Integer isDefault,Integer userId);

}
