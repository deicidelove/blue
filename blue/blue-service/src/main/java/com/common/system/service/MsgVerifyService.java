package com.common.system.service;

import java.util.Date;

import com.common.system.dto.MessageSendResult;
import com.common.system.entity.MsgVerify;

public interface MsgVerifyService {
	
	public void insert(MsgVerify verify);
	
	public MsgVerify getUnderTime(String openId, Date curTime);
	
	public MessageSendResult messageSend(String phoneNumber, String verifyCode);
}
