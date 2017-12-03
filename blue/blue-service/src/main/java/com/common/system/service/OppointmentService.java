/**
 * 
 */
package com.common.system.service;

import java.util.List;

import com.common.system.entity.BlueOppointment;
import com.common.system.entity.BlueStaff;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
public interface OppointmentService {
	/**
	 * 获取预约列表
	 * 
	 * @param type           
	 * @return
	 */
	public PageBean<BlueOppointment> getOppointmentList(String type, int startPage,
			int limitLength,String date);
	
	/**
	 * 添加
	 */
	public Result<Integer> addOppo(int staffId,String date, String payMoney,String userId,
			Integer pationId,Integer scheduleId);
	
	public Result<Integer> deleteOppo(int sid);
	
	public Result<Integer> updateOppo(String date,int sid);
	
	/**
	 * 查询 
	 * @param sid
	 * @return
	 */
	public Result<BlueOppointment> findOppo(int sid);
	
	public List<BlueOppointment> findByUserId(String userId);
	
	/**
	 * 查询是否预约
	 * @param staffId 医生
	 * @param pationId 就诊人
	 * @param userId 预约人
	 * @param orderTime 预约时间
	 * @return
	 */
	public Result<BlueOppointment> findOppo(Integer staffId,Integer pationId, String userId, String orderTime);

}
