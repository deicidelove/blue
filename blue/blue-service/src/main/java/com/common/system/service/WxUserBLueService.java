package com.common.system.service;

import java.util.List;

import com.common.system.entity.WxUserEntity;
import com.common.system.util.Result;
import com.common.system.weixin.dto.ApplyTokenResult;
import com.github.pagehelper.PageInfo;

public interface WxUserBLueService {
	
	public Result<Integer> save(WxUserEntity wxUserEntity);
	
	public Result<Integer> deleteById(String openId);
	
	public void updateJifen(WxUserEntity wxUserEntity);
	
	public WxUserEntity getById(String openId);
	
	public PageInfo<WxUserEntity> listForPage(Integer pageNum, Integer pageSize);
	
	/**
	 * <p>
	 * <code>listForPage</code>
	 * </p>
	 * 通过superOpenId查询推荐人
	 * @author taowang6
	 * @param pageNum
	 * @param pageSize
	 * @param superOpenId
	 * @return
	 */
	public PageInfo<WxUserEntity> listForPage(Integer pageNum, Integer pageSize, String superOpenId);
	
	public List<WxUserEntity> list();
	
	/**
	 *  更新tip
	 * @param openId
	 * @param isShowTip
	 */
	public void updateTip(String openId, Boolean isShowTip);
	
	/**
	 * 根据电话号码获取二维码连接
	 * @param openId
	 * @return
	 */
	String getUserQRCode(String openId);
	
	/**
	 * 申请微信公众号Token
	 * @return 微信服务器返回结果
	 */
	ApplyTokenResult applyToken();
	
	/**
	 * 注册更新用户信息
	 * @param userName
	 * @param phoneNumber
	 * @param openId
	 */
	void updateUserInfo(String openId, String userName, String phoneNumber);
	
	
	/**
	 * 更新合成图片路径
	 * @param openId
	 * @param combinedPicturePath
	 */
	void updateCombinedPicturePath(String openId, String combinedPicturePath);
	
	
	/**
	 * 更新用户二维码信息
	 * @param openId 微信OpenId
	 * @param ticket 微信邀请ticket
	 * @param qrcodeUrl 二维码地址
	 */
	public void updateUserQRCodeUrl(String openId, String ticket, String qrcodeUrl);
}
