package com.common.system.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.common.system.entity.ActEntity;
import com.common.system.entity.GivingEntity;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.ActService;
import com.common.system.service.GivingService;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsService;
import com.common.system.service.MsgVerifyService;
import com.common.system.service.WxUserBLueService;
import com.google.common.base.Strings;

@Controller
public class LucyNumberSelectorController {
	private Logger logger = LoggerFactory.getLogger(LucyNumberSelectorController.class);
	// 时间格式化
	private static final SimpleDateFormat Format = new SimpleDateFormat("HHmmssSSS");
	// 重庆时时彩幸运号码(默认为0)
	private static final Integer SSCLucyNumber = 0;
	// 原始幸运数
	private static final Integer InitLucyNumber = 10000001;
	
	// 管理员电话号码
	@Value("${mysubmail.message.admin.phoneNumber}")
	private String adminPhoneNumber;
	
	private @Resource ActService actService;
	private @Resource GoodsService goodsService;
	private @Resource GivingService givingService;
	private @Resource GoodsConsumerRelateService goodsConsumerRelateService;
	private @Resource MsgVerifyService msgVerifyService;
	private @Resource WxUserBLueService wxUserBLueService;
	
	
	@PostConstruct
	public void init() {
		ScheduledExecutorService schduler = Executors.newScheduledThreadPool(4);
		schduler.scheduleAtFixedRate(new SelectorSchduler(), 0, 3, TimeUnit.MINUTES);
	}
	
	
	class SelectorSchduler implements Runnable {
		
		@Override
		public void run() {
			try {
				List<GoodsEntity> actGoodsEntities = goodsService.listNoneOverGoods();
				if ( !CollectionUtils.isEmpty(actGoodsEntities) ) {
					for( GoodsEntity goodsEntity : actGoodsEntities ) {
						Integer actId = goodsEntity.getActId();
						Integer goodsId = goodsEntity.getGoodsId();
						ActEntity actEntity = actService.getById(actId);
						List<GoodsConsumerRelateEntity> goodConsumerEntities = goodsConsumerRelateService.listUsed(actId, goodsId);
						// 参与人数必须大于活动参与总人数
						Integer actTotalNum = actEntity.getActTotalNum();
						if ( !CollectionUtils.isEmpty(goodConsumerEntities) && goodConsumerEntities.size() >= actTotalNum ) {
							// 1. 进行排序
							Collections.sort(goodConsumerEntities, new GoodConsumerEntityComparator());
							// 2. 截取最后actTotalNum个
							List<GoodsConsumerRelateEntity> latestEntities = goodConsumerEntities.subList(0, actTotalNum);
							// 3. 累加时间
							Long timeSum = timeSum(latestEntities);
							// 4. 累加时间+重庆时时彩幸运号码
							Long lucyNumber = timeSum + SSCLucyNumber;
							// 5. 除以总需人数得到余数
							Long lucyRemain = lucyNumber % actTotalNum;
							// 6. 累加初始幸运数,即得到最终幸运数
							Long finalLucyNumber = lucyRemain + InitLucyNumber;
							// 7. 找出这个人
							GoodsConsumerRelateEntity lucyMan = findLucyConsumer(finalLucyNumber, goodConsumerEntities);
							// 8. 最终保存
							if ( lucyMan != null ) {
								givingService.saveGiving(wrapGiving(lucyMan));
								goodsService.updateActGoodsOver(goodsId);
								// 发送结果短信给管理员和幸运中奖人
								String lucyManPhoneNumber = getLucyUserPhoneNumber(lucyMan);
								if ( !Strings.isNullOrEmpty(lucyManPhoneNumber) ) {
									// 发送短信给幸运中奖人
									sendMessageToLucyMan(lucyManPhoneNumber, goodsEntity);
									// 发送通知给管理员
									sendMessageToAdmin(lucyManPhoneNumber, actEntity);
								}
								logger.warn("活动{},商品{}的幸运号码号码是{},幸运者是{}", actId, goodsId, finalLucyNumber, lucyMan.getConsumerId());
							} else {
								logger.warn("活动{},商品{}的消费者中没有选择幸运号码号码{}的人,本次结束", actId, goodsId, finalLucyNumber);
							}
						} else {
							logger.warn("活动{},商品{}的参与人数小于指定的活动参与人数{},本次不选择幸运号码", actId, goodsId, actTotalNum);
						}
					}
				} else {
					logger.warn("没有正在进行中的活动商品,本次幸运消费者选择结束");
				}
			} catch ( Exception e ) {
				logger.error("在选择中奖号码时发生异常", e);
			}
		}
		
		// 找出幸运用户的电话号码
		private String getLucyUserPhoneNumber(GoodsConsumerRelateEntity lucyMan) {
			WxUserEntity userEntity = wxUserBLueService.getById(lucyMan.getOpenId());
			if ( userEntity != null ) {
				if ( Strings.isNullOrEmpty(userEntity.getTel()) ) {
					logger.error("由于用户{}没有电话号码,所以无法发送短信", userEntity );
					return null;
				} else {
					return userEntity.getTel();
				}
			} else {
				logger.error("用户:{}通过appId无法找到该用户", lucyMan);
			}
			return null;
		}
		
		
		// 发送短信给中奖人
		private void sendMessageToLucyMan(String lucyManPhoneNumber, GoodsEntity goodsEntity) {
			JSONObject vars = new JSONObject();
			vars.put("luckyNumber", lucyManPhoneNumber);
			vars.put("goodName", goodsEntity.getGoodsName());
			msgVerifyService.sendMessage(lucyManPhoneNumber, vars);
		}
		
		// 发送短信给管理员
		private void sendMessageToAdmin(String lucyManPhoneNumber, ActEntity actEntity) {
			JSONObject vars = new JSONObject();
			vars.put("luckyNumber", lucyManPhoneNumber);
			vars.put("actName", actEntity.getActName());
			msgVerifyService.sendMessage(adminPhoneNumber, vars);
		}

	}
	
	
	class GoodConsumerEntityComparator implements Comparator<GoodsConsumerRelateEntity> {

		@Override
		public int compare(GoodsConsumerRelateEntity o1, GoodsConsumerRelateEntity o2) {
			// 倒序
			return -o1.getCreateTime().compareTo(o2.getCreateTime());
		}

	}
	
	
	private Long timeSum(List<GoodsConsumerRelateEntity> latestEntities) {
		Long timeSum = 0l;
		for ( GoodsConsumerRelateEntity entity : latestEntities ) {
			String formateDate = Format.format(entity.getCreateTime());
			timeSum += Long.valueOf(formateDate);
		}
		return timeSum;
	}
	
	
	private GoodsConsumerRelateEntity findLucyConsumer(
			Long finalLucyNumber, List<GoodsConsumerRelateEntity> goodConsumerEntities) {
		String lucyNumberStr = finalLucyNumber.toString();
		for( GoodsConsumerRelateEntity entity : goodConsumerEntities ) {
			if ( lucyNumberStr.equals(entity.getConsumerGivingCode()) ) {
				return entity;
			}
		}
		return null;
	}
	
	
	private GivingEntity wrapGiving(GoodsConsumerRelateEntity lucyMan) {
		GivingEntity ge = new GivingEntity();
		ge.setActId(lucyMan.getActId());
		ge.setCreateTime(new Date());
		ge.setGivingCode(lucyMan.getConsumerGivingCode().toString());
		ge.setGoodsId(lucyMan.getGoodsId());
		ge.setOpenId(lucyMan.getOpenId());
		return ge;
	}
}
