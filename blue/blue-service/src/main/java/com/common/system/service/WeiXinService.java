package com.common.system.service;

import com.common.system.entity.RcWeiXinUser;
import com.common.system.weixin.dto.ApplyTokenResult;
import com.common.system.weixin.dto.CreateTicketResult;

/** 微信相关服务 **/
public interface WeiXinService {
	
	/**
	 * 申请微信公众号Token
	 * @return 微信服务器返回结果
	 */
	ApplyTokenResult applyToken();
	
	
	/**
	 * 为用户申请永久Ticket
	 * @param token
	 * @param sceneStr
	 * @return
	 */
	CreateTicketResult createTicket(String token, String sceneStr);
	
	
	/**
	 * 获取二维码
	 * @param ticket
	 * @return
	 */
	String showQRCode(String ticket);
	
	
	/**
	 * 根据电话号码获取二维码连接
	 * @param openId
	 * @return
	 */
	String getUserQRCode(String openId);
	
	
	/**
	 * 注册更新用户信息
	 * @param userName
	 * @param phoneNumber
	 * @param openId
	 */
	void updateUserInfo(String openId, String userName, String phoneNumber, String ticket, String qrcodeUrl);
	
	
	/**
	 * 插入微信用户
	 * @param user 用户
	 */
	void insertWeiXinUser(RcWeiXinUser user);
	
	
	/**
	 * 更新用户邀请书
	 * @param ticket
	 */
	void increaseInviteCountByTickt(String ticket);
	
}