/**
 * 
 */
package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author amkong
 *
 */
public class BlueNeedWork implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8933478731123803616L;
	
	private Integer sid;
	
	private String title;
	
	private Integer needNum;
	
	private String education = "";
	
	private String experience = "";
	
	private String wages = "";
	
	private String workTime = "";
	
	private String workAddress = "";
	
	private String description = "";
	
	private String requirement = "";
	
	private String fringeBenefits = "";
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the needNum
	 */
	public Integer getNeedNum() {
		return needNum;
	}

	/**
	 * @param needNum the needNum to set
	 */
	public void setNeedNum(Integer needNum) {
		this.needNum = needNum;
	}

	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}

	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}

	/**
	 * @return the wages
	 */
	public String getWages() {
		return wages;
	}

	/**
	 * @param wages the wages to set
	 */
	public void setWages(String wages) {
		this.wages = wages;
	}

	/**
	 * @return the workTime
	 */
	public String getWorkTime() {
		return workTime;
	}

	/**
	 * @param workTime the workTime to set
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	/**
	 * @return the workAddress
	 */
	public String getWorkAddress() {
		return workAddress;
	}

	/**
	 * @param workAddress the workAddress to set
	 */
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the requirement
	 */
	public String getRequirement() {
		return requirement;
	}

	/**
	 * @param requirement the requirement to set
	 */
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	/**
	 * @return the fringeBenefits
	 */
	public String getFringeBenefits() {
		return fringeBenefits;
	}

	/**
	 * @param fringeBenefits the fringeBenefits to set
	 */
	public void setFringeBenefits(String fringeBenefits) {
		this.fringeBenefits = fringeBenefits;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
