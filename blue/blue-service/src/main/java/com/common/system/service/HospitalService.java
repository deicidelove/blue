/**
 * 
 */
package com.common.system.service;

import com.common.system.entity.BlueHospital;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface HospitalService {

	public PageBean<BlueHospital> findBlueHospitals(int type, int startPage,
			int limitLength);

	public Result<String> deleteHospital(int sid);

	public Result<BlueHospital> findHospital(int sid);

	public Result<String> updateHospital(int type, int sid, String context,
			String title);

	public Result<String> addHospital(int type, String context, String title);

}
