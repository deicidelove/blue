/**
 * 
 */
package com.common.system.service;

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
	public Result<Integer> addOppo(int staffId,String date, String payMoney,String userId,Integer pationId);
	
	public Result<Integer> deleteOppo(int sid);
	
	public Result<Integer> updateOppo(String date,int sid);
	
	/**
	 * 查询 
	 * @param sid
	 * @return
	 */
	public Result<BlueOppointment> findOppo(int sid);

}
