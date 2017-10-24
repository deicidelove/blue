package com.common.system.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.common.system.dto.MessageSendResult;
import com.common.system.entity.MsgVerify;
import com.common.system.service.MsgVerifyService;
import com.common.system.util.StandardJSONResult;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@Controller
public class LoginController {
	
	// 短信验证服务
	@Resource MsgVerifyService msgVerifyService;
	
	
	@ResponseBody
	@RequestMapping("/msgVerify")
	public String msgVerify(String weixinAppId, String phoneNumber) {
		MsgVerify verify = msgVerifyService.getUnderTime(weixinAppId, new Date());
		if ( verify != null ) {
			return JSON.toJSONString(StandardJSONResult.getFailedInstance("验证短信已发送，请稍后再试"));
		}
		
		// 验证码
		String checkCode = randCheckCode();
		MessageSendResult result = msgVerifyService.messageSend(phoneNumber, checkCode);
		if ( result == null ) {
			return JSON.toJSONString(StandardJSONResult.getFailedInstance("内部异常，请稍后再试"));
		} else if ( result.getStatus() != null && result.getStatus().equals("error") ) {
			return JSON.toJSONString(StandardJSONResult.getFailedInstance("短信发送失败，请稍后再试"));
		} else {
			msgVerifyService.insert(new MsgVerify(weixinAppId, checkCode, oneMinuteAfer()));
			return JSON.toJSONString(StandardJSONResult.getSuccessInstance(JSON.toJSONString(new SendResult(checkCode))));
		}
	}

	
	
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
