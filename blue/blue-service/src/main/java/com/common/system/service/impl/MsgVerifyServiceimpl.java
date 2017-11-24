package com.common.system.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.system.dao.MsgVerifyDao;
import com.common.system.dto.MessageSendResult;
import com.common.system.entity.MsgVerify;
import com.common.system.service.MsgVerifyService;
import com.common.system.util.RequestEncoder;

@Service("msgVerifyService")
public class MsgVerifyServiceimpl implements MsgVerifyService {
	private static final Logger logger = LoggerFactory.getLogger(MsgVerifyServiceimpl.class);
	// 时间戳接口配置
	private static final String TIMESTAMP = "https://api.mysubmail.com/service/timestamp";
	// API 请求接口配置
	private static final String URL="https://api.mysubmail.com/message/xsend";
	private static final String TYPE_MD5 = "md5";
	private static final String TYPE_SHA1 = "sha1";
	
	// 短信发送平台AppId
	@Value("${mysubmail.message.appId}")
	private String messageAppId;
	// 短信发送平台AppKey
	@Value("${mysubmail.message.appKey}")
	private String messageAppKey;
	// 短信发送平台项目
	@Value("${mysubmail.message.project}")
	private String messageProject;
	
	@Resource MsgVerifyDao msgVerifyDao;
	
	
	@Override
	public void insert(MsgVerify verify) {
		msgVerifyDao.insert(verify);
	}

	@Override
	public MsgVerify getUnderTime(String phoneNumber, Date curTime) {
		return msgVerifyDao.getUnderTime(phoneNumber, curTime);
	}
	
	public MessageSendResult sendVerifyMessage(String phoneNumber, String verifyCode) {
		JSONObject vars = new JSONObject();
		vars.put("code", verifyCode);
		vars.put("user", phoneNumber);
		return sendMessage(phoneNumber, vars);
	}
	
	
	// 发送短信
	public MessageSendResult sendMessage(String phoneNumber, JSONObject vars) {
		TreeMap<String, Object> requestData = new TreeMap<String, Object>();
		requestData.put("appid", messageAppId);
		requestData.put("project", messageProject);
		requestData.put("to", phoneNumber);
		if(!vars.isEmpty()){
			requestData.put("vars", vars.toString());
		}
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		@SuppressWarnings("deprecation")
		ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8);
		for(Map.Entry<String, Object> entry: requestData.entrySet()){
			String key = entry.getKey();
			Object value = entry.getValue();
			if(value instanceof String){
				builder.addTextBody(key, String.valueOf(value),contentType);
			}
		}	
		
		String signtype = "";
		if(signtype.equals(TYPE_MD5) || signtype.equals(TYPE_SHA1)){
			String timestamp = getTimestamp();
			requestData.put("timestamp", timestamp);
			requestData.put("sign_type", signtype);
			String signStr = messageAppId + messageAppKey 
					+ RequestEncoder.formatRequest(requestData) 
					+ messageAppId + messageAppKey;
			
			builder.addTextBody("timestamp", timestamp);
			builder.addTextBody("sign_type", signtype);
			builder.addTextBody("signature", RequestEncoder.encode(signtype, signStr), contentType);
		}else{
			builder.addTextBody("signature", messageAppKey, contentType);
		}
		
		HttpPost httpPost = new HttpPost(URL);
		httpPost.addHeader("charset", "UTF-8");
		httpPost.setEntity(builder.build());
		try{
			CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
			HttpResponse response = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			if(httpEntity != null){
				return JSON.parseObject(EntityUtils.toString(httpEntity, "UTF-8"), MessageSendResult.class);
			}
		}catch(Exception e){
			logger.error("在发送短信时发生异常", e);
		}
		return null;
	}
	
	
	private String getTimestamp(){
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
		HttpGet httpget = new HttpGet(TIMESTAMP);
		try{
			HttpResponse response = closeableHttpClient.execute(httpget);
			HttpEntity httpEntity = response.getEntity();
			String jsonStr = EntityUtils.toString(httpEntity,"UTF-8");
			if(jsonStr != null){
				JSONObject json = JSONObject.parseObject(jsonStr);
				return json.getString("timestamp");
			}
			closeableHttpClient.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}