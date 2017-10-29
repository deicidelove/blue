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
public class BlueWantWork implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1010829436027620045L;
	
	private Integer sid;
	
	private String name;
	
	private String phone;
	
	private String profession;
	
	private String wantJob;
	
	private String wantWage;
	
	private String describe;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the wantJob
	 */
	public String getWantJob() {
		return wantJob;
	}

	/**
	 * @param wantJob the wantJob to set
	 */
	public void setWantJob(String wantJob) {
		this.wantJob = wantJob;
	}

	/**
	 * @return the wantWage
	 */
	public String getWantWage() {
		return wantWage;
	}

	/**
	 * @param wantWage the wantWage to set
	 */
	public void setWantWage(String wantWage) {
		this.wantWage = wantWage;
	}

	/**
	 * @return the describe
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * @param describe the describe to set
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
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
