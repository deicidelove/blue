package com.common.system.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.common.system.dao.TokenDao;
import com.common.system.dao.WxuserDao;
import com.common.system.entity.RcToken;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.WxUserBLueService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.common.system.weixin.dto.ApplyTokenResult;
import com.common.system.weixin.dto.CreateTicketResult;
import com.common.system.weixin.dto.CreateTokenAction;
import com.common.system.weixin.dto.CreateTokenActionInfo;
import com.common.system.weixin.dto.CreateTokenActionScene;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
@Service("wxUserBLueService")
public class WxUserBlueServiceImpl implements WxUserBLueService {
	private static final Logger logger = LoggerFactory.getLogger(WxUserBlueServiceImpl.class);
	
	// 申请Token请求地址
	public static final String ApplyTokenURL = "https://api.weixin.qq.com/cgi-bin/token";
	// 创建二维码ticket
	public static final String CreateTiecktURL = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
	// 通过ticket换取二维码
	public static final String ShowQRCodeURL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	
	// 公众号AppId
	@Value("${wechat.mp.appId}") private String weixinAppId;
	// 公众号Secret
	@Value("${wechat.mp.secret}") private String weixinSecret;
	
	@Resource WxuserDao wxuserDao;
	@Resource TokenDao tokenDao;
	
	
	@Override
	public Result<Integer> save(WxUserEntity wxUserEntity) {
		Result<Integer> result = new Result<Integer>();
		try {
			wxuserDao.save(wxUserEntity);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			logger.error("WxUserServiceImpl save error!", e );
		}
		return result;
	}

	@Override
	public Result<Integer> deleteById(String openId) {
		Result<Integer> result = new Result<Integer>();
		try {
			wxuserDao.deleteById(openId);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
		} catch (Exception e) {
			logger.error("WxUserServiceImpl deleteById error!", e );
		}
		return result;
	}

	@Override
	public void updateJifen(WxUserEntity wxUserEntity) {
		try {
			wxuserDao.updateJifen(wxUserEntity);
		} catch (Exception e) {
			logger.error("WxUserServiceImpl updateJifen error!", e );
		}

	}

	@Override
	public WxUserEntity getById(String openId) {
		WxUserEntity wxUserEntity = null;
		try {
			wxUserEntity = wxuserDao.seleteById(openId);
		} catch (Exception e) {
			logger.error("WxUserServiceImpl getById error!", e );
		}
		return wxUserEntity;
	}

	@Override
	public PageInfo<WxUserEntity> listForPage(Integer pageNum, Integer pageSize) {
		PageInfo<WxUserEntity> result = new PageInfo<WxUserEntity>();
		try {
			List<WxUserEntity> resultList = wxuserDao.seleteList(pageNum, pageSize);
			result.setList(resultList);
		} catch (Exception e) {
			logger.error("WxUserServiceImpl listForPage error!", e );
		}
		return result;
	}

	@Override
	public List<WxUserEntity> list() {
		List<WxUserEntity> resultList = Lists.newArrayList();
		try {
			resultList = wxuserDao.seleteList();
		} catch (Exception e) {
			logger.error("WxUserServiceImpl list error!", e );
		}
		return resultList;
	}

	@Override
	public void updateTip(String openId, Boolean isShowTip) {
		try {
			wxuserDao.updateTip(openId, isShowTip);
		} catch (Exception e) {
			logger.error("updateTip error!", e);
		}
	}

	@Override
	public PageInfo<WxUserEntity> listForPage(Integer pageNum,
			Integer pageSize, String superOpenId) {
		PageInfo<WxUserEntity> result = new PageInfo<WxUserEntity>();
		try {
			List<WxUserEntity> resultList = wxuserDao.seleteList(pageNum, pageSize, superOpenId);
			result.setList(resultList);
		} catch (Exception e) {
			logger.error("WxUserServiceImpl listForPage error!", e );
		}
		return result;
	}
	
	
	@Override
	public String getUserQRCode(String openId) {
		Assert.notNull(openId, "openId不可为空");
		
		WxUserEntity user = wxuserDao.seleteById(openId);
		if ( user != null && !Strings.isNullOrEmpty(user.getQrCodeUrl())) {
			return user.getQrCodeUrl();
		} else {
			// 1. 申请token
			ApplyTokenResult applyTokenResult = applyToken();
			// 不为空,且请求成功
			if ( applyTokenResult != null && !Strings.isNullOrEmpty(applyTokenResult.getAccess_token()) ) {
				// 2. 申请ticket
				CreateTicketResult createTicketResult = createTicket(applyTokenResult.getAccess_token(), openId);
				if ( createTicketResult != null ) {
					// 3. 保存
					String qrcodeUrl = ShowQRCodeURL + "?ticket=" + createTicketResult.getTicket();
					wxuserDao.updateUserQRCodeUrl(openId, createTicketResult.getTicket(), qrcodeUrl);
					return qrcodeUrl;
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
						token.setAppId(weixinAppId);
						token.setToken(applyTokenResult.getAccess_token());
						// IP地址不固定导致token在5分钟内过期,因此暂时将有效期设为5分钟
						token.setExpireTime(timeAfer(300));
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
	public void updateUserInfo(String openId, String userName, String phoneNumber, String ticket, String qrcodeUrl) {
		wxuserDao.updateUserInfo(openId, phoneNumber, userName, ticket, qrcodeUrl);
	}
	
	
	@Override
	public void updateCombinedPicturePath(String openId, String combinedPicturePath) {
		wxuserDao.updateCombinedPicturePath(openId, combinedPicturePath);
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
