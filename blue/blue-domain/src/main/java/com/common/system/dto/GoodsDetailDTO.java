package com.common.system.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.common.system.entity.WxDetailEntity;

public class GoodsDetailDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5679756179269124681L;

	private Integer actId;
	
	private Integer goodsId;
	
	private String goodsName;
	
	private String goodsTitle;
	
	private BigDecimal goodsPrice;
	/**
	 * 活动总数
	 */
	private Integer actTotalNum;
	
	/**
	 *  剩余个数
	 */
	private Integer remainingNum;
	
	/**
	 * 参与个数
	 */
	private Integer participantsNum;
	
	/**
	 * 商品详情
	 */
	private String goodsDetail;
	
	/**
	 * 幸运号
	 */
	private List<String> givingCodeList;
	
	/**
	 * 参与的人
	 */
	private List<GoodsDetailWxDTO> goodsDetailWxDTOList;

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
	 * @return the goodsTitle
	 */
	public String getGoodsTitle() {
		return goodsTitle;
	}

	/**
	 * @param goodsTitle the goodsTitle to set
	 */
	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	/**
	 * @return the actTotalNum
	 */
	public Integer getActTotalNum() {
		return actTotalNum;
	}

	/**
	 * @param actTotalNum the actTotalNum to set
	 */
	public void setActTotalNum(Integer actTotalNum) {
		this.actTotalNum = actTotalNum;
	}

	/**
	 * @return the remainingNum
	 */
	public Integer getRemainingNum() {
		return remainingNum;
	}

	/**
	 * @param remainingNum the remainingNum to set
	 */
	public void setRemainingNum(Integer remainingNum) {
		this.remainingNum = remainingNum;
	}

	/**
	 * @return the participantsNum
	 */
	public Integer getParticipantsNum() {
		return participantsNum;
	}

	/**
	 * @param participantsNum the participantsNum to set
	 */
	public void setParticipantsNum(Integer participantsNum) {
		this.participantsNum = participantsNum;
	}

	/**
	 * @return the goodsDetail
	 */
	public String getGoodsDetail() {
		return goodsDetail;
	}

	/**
	 * @param goodsDetail the goodsDetail to set
	 */
	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}

	/**
	 * @return the givingCodeList
	 */
	public List<String> getGivingCodeList() {
		return givingCodeList;
	}

	/**
	 * @param givingCodeList the givingCodeList to set
	 */
	public void setGivingCodeList(List<String> givingCodeList) {
		this.givingCodeList = givingCodeList;
	}

	/**
	 * @return the goodsDetailWxDTOList
	 */
	public List<GoodsDetailWxDTO> getGoodsDetailWxDTOList() {
		return goodsDetailWxDTOList;
	}

	/**
	 * @param goodsDetailWxDTOList the goodsDetailWxDTOList to set
	 */
	public void setGoodsDetailWxDTOList(List<GoodsDetailWxDTO> goodsDetailWxDTOList) {
		this.goodsDetailWxDTOList = goodsDetailWxDTOList;
	}

	/**
	 * @return the goodsPrice
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * @param goodsPrice the goodsPrice to set
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

}
