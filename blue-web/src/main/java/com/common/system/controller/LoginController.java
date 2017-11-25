package com.common.system.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.common.system.dto.MessageSendResult;
import com.common.system.entity.MsgVerify;
import com.common.system.service.MsgVerifyService;
import com.common.system.service.WxUserBLueService;
import com.common.system.util.CookieUtil;
import com.common.system.util.StandardJSONResult;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	// 短信验证服务
	@Resource MsgVerifyService msgVerifyService;
	@Resource WxUserBLueService wxUserBLueService;
	
	
	// 注册首页
	@RequestMapping("/register")
	public ModelAndView registerIndex(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/register");
		return modelAndView;
	}
	
	// 登陆首页
	@RequestMapping("/login")
	public ModelAndView loginIndex(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login");
		return modelAndView;
	}
	
	
	// 发送验证码
	@ResponseBody
	@RequestMapping("/msgVerify")
	public String msgVerify(String phoneNumber) {
		MsgVerify verify = msgVerifyService.getUnderTime(phoneNumber, new Date());
		if ( verify != null ) {
			return JSON.toJSONString(StandardJSONResult.getFailedInstance("验证短信已发送，请稍后再试"));
		}
		
		// 验证码
		String checkCode = randCheckCode();
		MessageSendResult result = msgVerifyService.sendVerifyMessage(phoneNumber, checkCode);
		if ( result == null ) {
			return JSON.toJSONString(StandardJSONResult.getFailedInstance("内部异常，请稍后再试"));
		} else if ( result.getStatus() != null && result.getStatus().equals("error") ) {
			return JSON.toJSONString(StandardJSONResult.getFailedInstance("短信发送失败，请稍后再试"));
		} else {
			msgVerifyService.insert(new MsgVerify(phoneNumber, checkCode, oneMinuteAfer()));
			return JSON.toJSONString(StandardJSONResult.getSuccessInstance(JSON.toJSONString(new SendResult(checkCode))));
		}
	}

	
	// 用户注册
	@ResponseBody
	@RequestMapping("/userRegister")
	public String userRegister(HttpServletRequest request, ModelAndView modelAndView, String userName, String phoneNumber, String openId, String checkCode) {
		openId = CookieUtil.getCookieValue(request, "openId");
		openId = "orIMpwL34MilU3-Y35pQAOt6V-70";
		MsgVerify verify = msgVerifyService.getUnderTime(phoneNumber, new Date());
		if ( verify != null ) {
			if ( verify.getCheckCode().equals(checkCode) ) {
				// 写入到用户表中
				String qrcodeUrl = wxUserBLueService.getUserQRCode(openId);
				String ticket = qrcodeUrl.substring(qrcodeUrl.lastIndexOf('=')+1, qrcodeUrl.length());
				wxUserBLueService.updateUserInfo(openId, userName, phoneNumber, ticket, qrcodeUrl);
				return JSON.toJSONString(StandardJSONResult.getSuccessInstance());
			} else {
				RegisterResult registerResult = new RegisterResult(false, "验证码错误,请重新输入!");
				return JSON.toJSONString(StandardJSONResult.getFailedInstance(JSON.toJSONString(registerResult)));
			}
		} else {
			RegisterResult registerResult = new RegisterResult(false, "验证码已过期,请重新验证!");
			return JSON.toJSONString(StandardJSONResult.getFailedInstance(JSON.toJSONString(registerResult)));
		}
	}
	
	
	// 用户登陆
	@ResponseBody
	@RequestMapping("/userLogin")
	public String userLogin(ModelAndView modelAndView, String phoneNumber, String checkCode) {
		MsgVerify verify = msgVerifyService.getUnderTime(phoneNumber, new Date());
		if ( verify != null ) {
			if ( verify.getCheckCode().equals(checkCode) ) {
				// 写入到用户表中
				return JSON.toJSONString(StandardJSONResult.getSuccessInstance());
			} else {
				RegisterResult registerResult = new RegisterResult(false, "验证码错误,请重新输入!");
				return JSON.toJSONString(StandardJSONResult.getFailedInstance(JSON.toJSONString(registerResult)));
			}
		} else {
			RegisterResult registerResult = new RegisterResult(false, "验证码已过期,请重新验证!");
			return JSON.toJSONString(StandardJSONResult.getFailedInstance(JSON.toJSONString(registerResult)));
		}
	}
	
	
	// 发送验证码
	@RequestMapping("/registerSuccessedRedirect")
	public void userRegister(HttpServletRequest request , HttpServletResponse response) {
		String url = CookieUtil.getCookieValue(request, "original_url");
		if(StringUtils.isBlank(url)){
			url = "http://wx.njlxkq.com";
		}
		try {
			response.sendRedirect(url); 
		} catch (Exception e) {
			System.out.println(1);
		}
	}
	
	
	// 注册首页
	@RequestMapping("/kf")
	public ModelAndView kfIndex(ModelAndView modelAndView) {
		modelAndView.setViewName("/kfindex");
		return modelAndView;
	}
	
	//*****************************************************************************************************************
	class SendResult implements Serializable {
		private static final long serialVersionUID = -7839968363447731412L;
		private String checkCode;
		
		public SendResult(){}
		public SendResult(String checkCode) {
			this.checkCode = checkCode;
		}
		
		public String getCheckCode() {
			return checkCode;
		}

		public void setCheckCode(String checkCode) {
			this.checkCode = checkCode;
		}
		
	}
	
	
	class RegisterResult implements Serializable {
		private static final long serialVersionUID = -8643659600067390987L;
		// 是否注册成功
		private boolean isSuccessed = false;
		// 错误信息
		private String errorMessage;
		
		public RegisterResult(){}
		public RegisterResult(boolean isSuccessed){
			this.isSuccessed = isSuccessed;
		}
		public RegisterResult(boolean isSuccessed, String errorMessage) {
			this.isSuccessed = isSuccessed;
			this.errorMessage = errorMessage;
		}
		
		public boolean isSuccessed() {
			return isSuccessed;
		}
		public void setSuccessed(boolean isSuccessed) {
			this.isSuccessed = isSuccessed;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
	}
	
	
	private Date oneMinuteAfer() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 1);
		return calendar.getTime();
	}
	
	private String randCheckCode() {
		List<Integer> randNumbers = Lists.newArrayListWithExpectedSize(4);
		for( int i = 0; i < 4; i++ ) {
			randNumbers.add(new Random().nextInt(10));
		}
		return Joiner.on("").join(randNumbers);
	}


}
