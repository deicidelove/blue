/**
 * 
 */
package com.common.system.dto;

import java.io.Serializable;

/**
 * @author amkong
 *
 */
public class ScheduleDto implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3649289401820659710L;

	private Integer scheduleId;
	
	/**
	 * 0:可预约 1：约满 2：不可预约
	 */
	private Integer Flag;

	

	/**
	 * @return the scheduleId
	 */
	public Integer getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * @return the flag
	 */
	public Integer getFlag() {
		return Flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Integer flag) {
		Flag = flag;
	}
	
	
	
	
	
	

}
