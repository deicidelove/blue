/**
 * 
 */
package com.common.system.service;

import com.common.system.entity.BlueDoctorSchedule;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
public interface DoctorSchedulEService {

	public PageBean<BlueDoctorSchedule> getDoctorList(int start, int limit,
			String date, String deptId);

	public Result<Integer> delete(Integer id);

	public Result<Integer> save(String date, String count, int staffId,
			String shiftTime);

	public Result<BlueDoctorSchedule> findBySid(int sid);

	public Result<Integer> update(String date, String count, int staffId,
			String shiftTime, int sid);

}
