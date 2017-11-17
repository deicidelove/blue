package com.common.system.controller.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.JifenLogEntity;
import com.common.system.entity.OrderEntity;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsService;
import com.common.system.service.JifenLogService;
import com.common.system.service.OrderService;
import com.common.system.service.WxUserBLueService;
import com.common.system.util.Result;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


@Service("payCommonService")
public class PayCommonService{

    @Resource(name = "wxPayService")
    private WxPayService wxService;
    
    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;
    
    @Resource
    private GoodsConsumerRelateService goodsConsumerRelateService;
    
    @Resource
    private JifenLogService jifenLogService;
    
    @Resource
    private WxUserBLueService wxUserBLueService;
	
    private static final Logger LOG = LoggerFactory.getLogger(PayCommonService.class);
    
	public Map<String, String> payGoods(String openId, String goodsId) {
    	Map<String, String> result = Maps.newHashMap();

		result.put("result_code", "success");
    	result.put("result_msg", "success");
    	
    	try {
    		Result<GoodsEntity> goodsResult = goodsService.getById(Integer.valueOf(goodsId));
        	GoodsEntity goodsEntity  = goodsResult.getData();
        	if(null == goodsEntity){
            	result.put("result_code", "fail");
            	result.put("result_msg", "商品不存在！");
            	return result;
        	}
        	if(StringUtil.isEmpty(openId)){
        		result.put("result_code", "fail");
            	result.put("result_msg", "请重新关注，或联系管理员！");
            	return result;
        	}
        	
    		//消费一个中奖号码
        	GoodsConsumerRelateEntity goodsConsumerRelateEntity = goodsConsumerRelateService.randomByGoodsId(goodsEntity.getGoodsId());
        	if(null == goodsConsumerRelateEntity){//中奖被消费完
        		result.put("result_code", "fail");
            	result.put("result_msg", "你来晚了，该商品被抢购完");
            	return result;
        	}
        	// 微信支付，生成preId
        	WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        	request.setDeviceInfo("WEB");
        	request.setBody("蓝鲟-充值");
        	Map<String, List<Map<String,Object>>> map = Maps.newHashMap();
        	
        	Map<String,Object> temp = Maps.newHashMap();
        	temp.put("goods_id", goodsEntity.getGoodsId());
        	temp.put("wxpay_goods_id", goodsEntity.getGoodsId());
        	temp.put("goods_name", goodsEntity.getGoodsName());
        	temp.put("goods_num", Integer.valueOf(1));
        	temp.put("price", goodsEntity.getGoodsPrice());
        	temp.put("goods_category", StringUtil.isEmpty(goodsEntity.getCategory())? "活动":goodsEntity.getCategory());
        	temp.put("body", goodsEntity.getGoodsTitle());
        	List<Map<String,Object>> tempList = Lists.newArrayList();
        	tempList.add(temp);
        	map.put("goods_detail", tempList);	
        	request.setDetail(JSON.toJSONString(map));
        	request.setOutTradeNo(UUID.randomUUID().toString().replaceAll("-", ""));
//        	request.setTotalFee((goodsEntity.getGoodsPrice().multiply(new BigDecimal(100))).intValue());
        	request.setTotalFee(1);
        	request.setSpbillCreateIp("123.12.12.123");
        	request.setNotifyURL("http://wx.njlxkq.com/pay/parseOrderNotifyResult");
        	request.setTradeType("JSAPI");
        	request.setOpenid(openId);
        	result = this.wxService.getPayInfo(request);
        	
        	OrderEntity orderEntity = new OrderEntity();
        	orderEntity.setGoodsId(Integer.valueOf(goodsId));
        	orderEntity.setGoodsNum(1);
        	orderEntity.setOpenId(openId);
        	orderEntity.setSource("wxPayCode");
        	orderEntity.setStatus("wzf");
        	orderEntity.setType("wxPayCode");
        	orderEntity.setOutTradeId(request.getOutTradeNo());
        	orderEntity.setPrePayId(result.get("prepayId"));
        	orderEntity.setPrice(goodsEntity.getGoodsPrice());
        	Integer orderInt = orderService.save(orderEntity);
        	if(null == orderInt){
        		result.put("result_code", "fail");
            	result.put("result_msg", "订单生成异常，请联系管理员！");
            	return result;
        	}
        	//消费中奖
        	goodsConsumerRelateEntity.setOpenId(openId);
        	goodsConsumerRelateEntity.setIsUsed(true);
        	goodsConsumerRelateEntity.setGivingCodeSource(String.valueOf(orderInt));
        	int updateResult = goodsConsumerRelateService.updateConsumer(goodsConsumerRelateEntity);
        	if(0 == updateResult){//中奖被消费完
        		result.put("result_code", "fail");
            	result.put("result_msg", "你来晚了，该商品被抢购完");
            	return result;
        	}
		} catch (WxPayException e) {
			LOG.error("支付异常", e);
    		result.put("result_code", "fail");
        	result.put("result_msg", "订单生成异常，请联系管理员！");
        	return result;
			
		}
    	return result;
	}

	public Map<String, String> payCZ(String openId, String goodsId, Float czFre) {
		Map<String, String> result =  Maps.newHashMap();
		try {
			result.put("result_code", "success");
			result.put("result_msg", "success");
	    	
        	// 微信支付，生成preId
        	WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        	request.setDeviceInfo("WEB");
        	request.setBody("蓝鲟-充值");
        	Map<String, List<Map<String,Object>>> map = Maps.newHashMap();
        	
        	Map<String,Object> temp = Maps.newHashMap();
        	temp.put("goods_id", "-1");
        	temp.put("wxpay_goods_id", "-1");
        	temp.put("goods_name", "充值");
        	temp.put("goods_num", Integer.valueOf(1));
        	temp.put("price", czFre);
        	temp.put("goods_category", "充值");
        	temp.put("body", "充值");
        	List<Map<String,Object>> tempList = Lists.newArrayList();
        	tempList.add(temp);
        	map.put("goods_detail", tempList);	
        	request.setDetail(JSON.toJSONString(map));
        	request.setOutTradeNo(UUID.randomUUID().toString().replaceAll("-", ""));
//        	request.setTotalFee((goodsEntity.getGoodsPrice().multiply(new BigDecimal(100))).intValue());
        	request.setTotalFee((new BigDecimal(czFre).multiply(new BigDecimal(100))).intValue());
        	request.setSpbillCreateIp("123.12.12.123");
        	request.setNotifyURL("http://wx.njlxkq.com/pay/parseOrderNotifyResult");
        	request.setTradeType("JSAPI");
        	request.setOpenid(openId);
        	result = this.wxService.getPayInfo(request);
			
        	OrderEntity orderEntity = new OrderEntity();
        	orderEntity.setGoodsId(Integer.valueOf(goodsId));
        	orderEntity.setGoodsNum(1);
        	orderEntity.setOpenId(openId);
        	orderEntity.setSource("wxczPayCode");
        	orderEntity.setStatus("wzf");
        	orderEntity.setType("wxczPayCode");
        	orderEntity.setOutTradeId(request.getOutTradeNo());
        	orderEntity.setPrePayId(result.get("prepayId"));
        	orderEntity.setPrice(new BigDecimal(czFre));
        	orderEntity.setJifen((new BigDecimal(czFre).multiply(new BigDecimal(100))).intValue());
        	orderService.save(orderEntity);
        	
        	//用户添加积分
        	JifenLogEntity jifenLogEntity = new JifenLogEntity();
        	jifenLogEntity.setIsReverse(false);
        	jifenLogEntity.setOpenId(openId);
        	jifenLogEntity.setJifen((new BigDecimal(czFre).multiply(new BigDecimal(100))).intValue());
        	jifenLogEntity.setType("buy");
        	jifenLogService.save(jifenLogEntity);
        	
        	WxUserEntity wxUserEntity = wxUserBLueService.getById(openId);
        	wxUserEntity.setJifen(wxUserEntity.getJifen() + (new BigDecimal(czFre).multiply(new BigDecimal(100))).intValue());
        	wxUserBLueService.updateJifen(wxUserEntity);
        	
		} catch (Exception e) {
			LOG.error("充值异常！", e);
    		result.put("result_code", "fail");
        	result.put("result_msg", "订单生成异常，请联系管理员！");
        	return result;
		} 
		
		return result;
	}

}
