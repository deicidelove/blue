/**
 * 
 */
package com.common.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.common.system.entity.BlueHospital;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface HospitalService {

	public PageBean<BlueHospital> findBlueHospitals(String date,int type, int startPage,
			int limitLength);

	public Result<Integer> deleteHospital(int sid);

	public Result<BlueHospital> findHospital(int sid);

	public Result<Integer> updateHospital(int type, int sid, String context,
			String title,MultipartFile file);

	public Result<Integer> addHospital(int type, String context, String title,MultipartFile file);

}
