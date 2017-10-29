package com.common.system.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 图片
 * @author Blackgun
 *
 */
public class GoodsImgEntity {

	/**
	 * 图片id
	 */
	private Integer goodsImgId;	
	
	/**
	 * 商品Id
	 */
	private Integer goodsId;
	
	/**
	 * 图片类型 （列表图、详情图）
	 */
	private String imgType;
	/**
	 * 图地址
	 */
	private String goodsImgUrl;
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
	 * @return the goodsImgId
	 */
	public Integer getGoodsImgId() {
		return goodsImgId;
	}

	/**
	 * @param goodsImgId the goodsImgId to set
	 */
	public void setGoodsImgId(Integer goodsImgId) {
		this.goodsImgId = goodsImgId;
	}

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
	 * @return the imgType
	 */
	public String getImgType() {
		return imgType;
	}

	/**
	 * @param imgType the imgType to set
	 */
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	/**
	 * @return the goodsImgUrl
	 */
	public String getGoodsImgUrl() {
		return goodsImgUrl;
	}

	/**
	 * @param goodsImgUrl the goodsImgUrl to set
	 */
	public void setGoodsImgUrl(String goodsImgUrl) {
		this.goodsImgUrl = goodsImgUrl;
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
