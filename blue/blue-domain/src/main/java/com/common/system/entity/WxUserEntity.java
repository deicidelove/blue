package com.common.system.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 微信用户
 * 
 * @author Blackgun
 *
 */
public class WxUserEntity {
	/**
	 * 主键
	 */
	private Integer sid;
	/**
	 * 微信唯一标识
	 */
	private String openId;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 积分
	 */
	private Integer jifen;
	
	/**
	 * 推荐人
	 */
	private String superOpenId;
	/**
	 * 二维码
	 */
	private String qrCodeUrl;
	/**
	 * 是否删除
	 */
	private Boolean isDelete;
	/**
	 * 創建時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/**
	 * 是否展示tip
	 */
	private Boolean isShowTip ;

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
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the jifen
	 */
	public Integer getJifen() {
		return jifen;
	}

	/**
	 * @param jifen the jifen to set
	 */
	public void setJifen(Integer jifen) {
		this.jifen = jifen;
	}

	/**
	 * @return the superOpenId
	 */
	public String getSuperOpenId() {
		return superOpenId;
	}

	/**
	 * @param superOpenId the superOpenId to set
	 */
	public void setSuperOpenId(String superOpenId) {
		this.superOpenId = superOpenId;
	}

	/**
	 * @return the qrCodeUrl
	 */
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	/**
	 * @param qrCodeUrl the qrCodeUrl to set
	 */
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
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

	/**
	 * @return the isDelete
	 */
	public Boolean getIsDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the isShowTip
	 */
	public Boolean getIsShowTip() {
		return isShowTip;
	}

	/**
	 * @param isShowTip the isShowTip to set
	 */
	public void setIsShowTip(Boolean isShowTip) {
		this.isShowTip = isShowTip;
	}
	
}
