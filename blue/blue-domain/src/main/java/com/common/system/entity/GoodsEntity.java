package com.common.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3726609520222918316L;
	/**
	 * 商品主鍵
	 */
	private Integer goodsId;
	/**
	 * 活動主鍵
	 */
	private Integer actId;
	/**
	 * 商品名稱
	 */
	private String goodsName;
	/**
	 * 商品大圖Url
	 */
	private String goodsPicUrl;
	/**
	 * 商品詳情圖
	 */
	private String goodsDetailPicUrl;
	/**
	 * 創建時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 更新時間
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**
	 * @return the goodsId
	 */
	public Integer getGoodsId() {
		return goodsId;
	}
	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * @return the actId
	 */
	public Integer getActId() {
		return actId;
	}
	/**
	 * @param actId the actId to set
	 */
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * @return the goodsPicUrl
	 */
	public String getGoodsPicUrl() {
		return goodsPicUrl;
	}
	/**
	 * @param goodsPicUrl the goodsPicUrl to set
	 */
	public void setGoodsPicUrl(String goodsPicUrl) {
		this.goodsPicUrl = goodsPicUrl;
	}
	/**
	 * @return the goodsDetailPicUrl
	 */
	public String getGoodsDetailPicUrl() {
		return goodsDetailPicUrl;
	}
	/**
	 * @param goodsDetailPicUrl the goodsDetailPicUrl to set
	 */
	public void setGoodsDetailPicUrl(String goodsDetailPicUrl) {
		this.goodsDetailPicUrl = goodsDetailPicUrl;
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
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
