/**
 * 
 */
package com.common.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueAdvert;
import com.common.system.entity.BlueStaff;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
public interface DoctorService {
	
	/**
	 * 获取医生列表
	 * 
	 * @param type           
	 * @return
	 */
	public PageBean<BlueStaff> getDoctorList(String type, int startPage,
			int limitLength,String date);

	/**
	 * 删除某医生
	 * 
	 * @return
	 */
	public Result<Integer> deleteDoctor(int sid);

	/**
	 * 添加
	 */
	public Result<Integer> addDoctor(String jobNum,String name, int deptId,int sex,String phone,String introduce,String address);

	/**
	 * 查询 
	 * @param sid
	 * @return
	 */
	public Result<BlueStaff> findDoctor(int sid);

	/**
	 * 更新
	 * @return
	 */
	public Result<Integer> updateDoctor(String name, int deptId,
			int sex, String phone, int level, int sid,String introduce,String jobNum,String address);



}
