/**
 * 
 */
package com.common.system.entity;

import java.io.Serializable;

/**
 * @author amkong
 *
 */
public class BlueDeptDoctorPic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2632803607675047746L;
	
	private Integer sid;
	
	private String url = "";
	
	private String deptId = "";

	

	/**
	 * @return the sid
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

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
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	
	

}
