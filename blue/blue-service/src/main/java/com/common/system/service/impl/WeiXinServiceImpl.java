package com.common.system.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.common.system.dao.TokenDao;
import com.common.system.dao.WeiXinUserDao;
import com.common.system.entity.RcToken;
import com.common.system.entity.RcWeiXinUser;
import com.common.system.service.WeiXinService;
import com.common.system.weixin.dto.ApplyTokenResult;
import com.common.system.weixin.dto.CreateTicketResult;
import com.common.system.weixin.dto.CreateTokenAction;
import com.common.system.weixin.dto.CreateTokenActionInfo;
import com.common.system.weixin.dto.CreateTokenActionScene;
import com.google.common.base.Strings;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

@Service("weiXinService")
public class WeiXinServiceImpl implements WeiXinService {
	private static final Logger logger = LoggerFactory.getLogger(WeiXinServiceImpl.class);
	
	// 公众号AppId
	@Value("${wechat.mp.appId}") private String weixinAppId;
	// 公众号Secret
	@Value("${wechat.mp.secret}") private String weixinSecret;
	
	@Resource TokenDao tokenDao;
	@Resource WeiXinUserDao weiXinUserDao;
	
	// 申请Token请求地址
	public static final String ApplyTokenURL = "https://api.weixin.qq.com/cgi-bin/token";
	// 创建二维码ticket
	public static final String CreateTiecktURL = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
	// 通过ticket换取二维码
	public static final String ShowQRCodeURL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	
	
	
	
	@Override
	public String getUserQRCode(String openId) {
		Assert.notNull(openId, "openId不可为空");
		
		RcWeiXinUser user = weiXinUserDao.getUserOpenId(openId);
		if ( user != null && !Strings.isNullOrEmpty(user.getQrcodeUrl())) {
			return user.getQrcodeUrl();
		} else {
			// 1. 申请token
			ApplyTokenResult applyTokenResult = applyToken();
			// 不为空,且请求成功
			if ( applyTokenResult != null && !Strings.isNullOrEmpty(applyTokenResult.getAccess_token()) ) {
				// 2. 申请ticket
				CreateTicketResult createTicketResult = createTicket(applyTokenResult.getAccess_token(), openId);
				if ( createTicketResult != null ) {
					return ShowQRCodeURL + "?ticket=" + createTicketResult.getTicket();
				}
			}
			return null;
		}
	}
	
	
	@Override
	@Transactional
	public ApplyTokenResult applyToken() {
		try {
			RcToken token = tokenDao.getBeforeTime(weixinAppId, new Date());
			if ( token != null ) {
				ApplyTokenResult applyTokenResult = new ApplyTokenResult();
				applyTokenResult.setAccess_token(token.getToken());
				return applyTokenResult;
			} else {
				HttpResponse<String> response = Unirest.get(ApplyTokenURL).queryString("grant_type", "client_credential")
						.queryString("appid", weixinAppId)
						.queryString("secret", weixinSecret)
						.asString();
				if ( response != null ) {
					ApplyTokenResult applyTokenResult = JSON.parseObject(response.getBody(), ApplyTokenResult.class);
					if ( !Strings.isNullOrEmpty(applyTokenResult.getAccess_token()) ) {
						token = new RcToken();
						token.setId(UUID.randomUUID().toString());
						token.setAppId(weixinAppId);
						token.setToken(applyTokenResult.getAccess_token());
						token.setExpireTime(timeAfer(applyTokenResult.getExpires_in()));
						tokenDao.insert(token);
					}
					return applyTokenResult;
				}
			}
		} catch (Exception e) {
			logger.error("在申请微信公众号Token时发生异常", e);
		}
		return null;
	}


	@Override
	public CreateTicketResult createTicket(String token, String sceneStr) {
		Assert.notNull(token, "token不可为空");
		Assert.notNull(sceneStr, "sceneStr不可为空");
		
		CreateTokenAction action = getAction(sceneStr);
		try {
			HttpResponse<String> response = Unirest.post(CreateTiecktURL).queryString("access_token", token)
					.body(JSON.toJSONString(action))
					.asString();
			if ( response != null ) {
				return JSON.parseObject(response.getBody(), CreateTicketResult.class);
			}
		} catch (Exception e) {
			logger.error("在创建Ticket时发生异常", e);
		}
		return null;
	}

	
	@Override
	@Deprecated
	public String showQRCode(String ticket) {
		Assert.notNull(ticket, "ticket不可为空");
		
		try {
			HttpResponse<String> response = Unirest.get(ShowQRCodeURL).queryString("ticket", ticket)
					.queryString("appid", weixinAppId)
					.queryString("secret", weixinSecret)
					.asString();
			if ( response != null && !CollectionUtils.isEmpty(response.getHeaders()) ) {
				List<String> urls = response.getHeaders().get("url");
				if ( !CollectionUtils.isEmpty(urls) ) {
					return urls.get(0);
				}
			}
		} catch (Exception e) {
			logger.error("在申请微信公众号Token时发生异常", e);
		}
		return null;
	}
	
	
	@Override
	public void updateUserInfo(String openId, String userName, String phoneNumber, String ticket, String qrcodeUrl) {
		weiXinUserDao.updateUserInfo(openId, phoneNumber, userName, ticket, qrcodeUrl);
	}
	
	
	@Override
	public void insertWeiXinUser(RcWeiXinUser user) {
		weiXinUserDao.insert(user);
	}
	
	
	@Override
	public void increaseInviteCountByTickt(String ticket) {
		weiXinUserDao.increaseInviteCountByTickt(ticket);
	}
	
	
	//******************************************************************************************************************
	private CreateTokenAction getAction(String sceneStr) {
		CreateTokenAction action = new CreateTokenAction();
		action.setAction_name("QR_LIMIT_SCENE");
		CreateTokenActionInfo actionInfo = new CreateTokenActionInfo();
		CreateTokenActionScene scene = new CreateTokenActionScene();
		scene.setScene_str(sceneStr);
		actionInfo.setScene(scene);
		action.setAction_info(actionInfo);
		return action;
	}

	
	private Date timeAfer(Integer second) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	
}
