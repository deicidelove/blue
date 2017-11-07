/**
 * 
 */
package com.common.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueAdvert;
import com.common.system.entity.BlueNeedWork;
import com.common.system.entity.BlueWantWork;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
public interface NeedWorkService {
	
	public Result<BlueNeedWork> findBySid(int sid);
	
	public Result<Integer> saveAddWork(String addName, String phone, String educattion,String wantJob,
			String wageWant,String describe);

	
	
	public PageBean<BlueNeedWork> getNeedWorkList(Integer type,String date, int startPage,
			int limitLength);
	
	public PageBean<BlueWantWork> getWantWorkList(Integer type,String date, int startPage,
			int limitLength);
	
	public Result<Integer> deleteWork(int sid);
	public Result<Integer> wantDeleteWork(int sid);
	
	public Result<Integer> addWork(String title,Integer needNum,String education,String experience,String wages,
			String workTime,String workAddress,String description,String requirement,String fringeBenefits);

	public Result<Integer> updateWork(Integer sid,String title,Integer needNum,String education,String experience,String wages,
			String workTime,String workAddress,String description,String requirement,String fringeBenefits);


}
