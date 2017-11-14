/**
 * 
 */
package com.common.system.dto;

import java.io.Serializable;
import java.util.List;

import com.common.system.entity.BlueStaff;

/**
 * @author amkong
 *
 */
public class DoctorDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6091706525981026310L;

	private String url;
	
	private List<BlueStaff> doctors;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the doctors
	 */
	public List<BlueStaff> getDoctors() {
		return doctors;
	}

	/**
	 * @param doctors the doctors to set
	 */
	public void setDoctors(List<BlueStaff> doctors) {
		this.doctors = doctors;
	}
	
	

}
