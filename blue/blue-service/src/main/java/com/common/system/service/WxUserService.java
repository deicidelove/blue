package com.common.system.service;

import java.util.List;

import com.common.system.entity.WxUserEntity;
import com.common.system.util.Result;
import com.common.system.weixin.dto.ApplyTokenResult;
import com.common.system.weixin.dto.CreateTicketResult;
import com.github.pagehelper.PageInfo;

public interface WxUserService {
	
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
	 * 为用户申请永久Ticket
	 * @param token
	 * @param sceneStr
	 * @return
	 */
	CreateTicketResult createTicket(String token, String sceneStr);
	
	/**
	 * 注册更新用户信息
	 * @param userName
	 * @param phoneNumber
	 * @param openId
	 */
	void updateUserInfo(String openId, String userName, String phoneNumber, String ticket, String qrcodeUrl);
}
