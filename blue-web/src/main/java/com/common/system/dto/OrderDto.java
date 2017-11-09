/**
 * 
 */
package com.common.system.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.common.system.entity.BlueShift;

/**
 * @author amkong
 *
 */
public class OrderDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -99744422640913392L;

	
	private String date;
	
	private List<BlueShift> shifts;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the shifts
	 */
	public List<BlueShift> getShifts() {
		return shifts;
	}

	/**
	 * @param shifts the shifts to set
	 */
	public void setShifts(List<BlueShift> shifts) {
		this.shifts = shifts;
	}
	
	
}
