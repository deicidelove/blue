/**
 * 
 */
package com.common.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueDeptDoctorPic;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
public interface DoctorDeptPicService {
	
	public PageBean<BlueDeptDoctorPic> findPics(String type, int startPage,
			int limitLength,String date);
	
	public Result<Integer> deletePic(int sid);
	
	public Result<BlueDeptDoctorPic> findDeptPic(int sid);

	public Result<Integer> addDoctorDeptPic(Integer deptId,MultipartFile file);
	
	public Result<Integer> updateDoctorDeptPic(Integer sid,Integer deptId,MultipartFile file);


}
