package com.common.system.controller.jifen;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.dto.ActGoodsDTO;
import com.common.system.dto.GoodsDetailDTO;
import com.common.system.dto.GoodsDetailWxDTO;
import com.common.system.entity.ActEntity;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.entity.OrderEntity;
import com.common.system.entity.WxDetailEntity;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.ActService;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsImgService;
import com.common.system.service.GoodsService;
import com.common.system.service.OrderService;
import com.common.system.service.WxDetailService;
import com.common.system.service.WxUserService;
import com.common.system.util.Result;
import com.google.common.collect.Lists;

@RestController
@RequestMapping(value = "jifen")
public class JifenController {
	
	@Resource
	private ActService actService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private GoodsImgService goodsImgService;
	
	@Resource
	private GoodsConsumerRelateService goodsConsumerRelateService;
	
	@Resource
	private WxDetailService wxDetailService;
	
	@Resource
	private WxUserService wxUserService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
    private WxMpService wxService;
    
	@RequestMapping(value = "index",method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("/jifen/act");
        String code = request.getParameter("code");
        try {
        	if(StringUtils.isNoneBlank(code)){
        		WxMpOAuth2AccessToken token = wxService.oauth2getAccessToken(code);
        	}
        	System.out.println(1);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "getlist")
    public List<ActGoodsDTO> getlist(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
		List<ActGoodsDTO> resultList = Lists.newArrayList();
		List<GoodsEntity> goodsList = goodsService.list();
		if(CollectionUtils.isEmpty(goodsList)){
			return resultList;
		}
		for(GoodsEntity goodsEntity : goodsList){
			//获取活动信息，活动总数
			ActEntity actEntity = actService.getById(goodsEntity.getActId());
			if(null == actEntity){
				continue;
			}
			ActGoodsDTO actGoodsDTO = new ActGoodsDTO();
			actGoodsDTO.setActTotalNum(actEntity.getActTotalNum());
			actGoodsDTO.setActId(goodsEntity.getActId());
			actGoodsDTO.setGoodsId(goodsEntity.getGoodsId());
			actGoodsDTO.setGoodsName(goodsEntity.getGoodsName());
			actGoodsDTO.setGoodsPrice(goodsEntity.getGoodsPrice());
			actGoodsDTO.setGoodsTitle(goodsEntity.getGoodsTitle());
			//获取已参加人数
			List<GoodsConsumerRelateEntity> goodsConsumerRelateList = goodsConsumerRelateService.list(goodsEntity.getActId(), goodsEntity.getGoodsId());
			actGoodsDTO.setParticipantsNum((goodsConsumerRelateList.size()> actEntity.getActTotalNum())?actEntity.getActTotalNum(): goodsConsumerRelateList.size() );
			actGoodsDTO.setRemainingNum(
					(actEntity.getActTotalNum() - goodsConsumerRelateList.size())<0?0:(actEntity.getActTotalNum() - goodsConsumerRelateList.size()));
			//获取图片
			GoodsImgEntity imgEntity = goodsImgService.findByGoodsId(goodsEntity.getGoodsId(), "list_img");
			if(null != imgEntity){
				actGoodsDTO.setListImg(imgEntity.getGoodsImgUrl());
			}
			resultList.add(actGoodsDTO);
		}
        return resultList;
    }
	
	@RequestMapping(value = "actdetail",method = RequestMethod.GET)
	public ModelAndView actdetail(ModelAndView modelAndView, Integer actId, Integer goodsId){
		
		String openId = "1";
		
        modelAndView.setViewName("/jifen/actDetail");
        GoodsDetailDTO detailDTO = new GoodsDetailDTO();
        //act
        detailDTO.setActId(actId);
        detailDTO.setGoodsId(goodsId);
        
        ActEntity actEntity = actService.getById(actId);
        
        //goods
        Result<GoodsEntity> goodsResult = goodsService.getById(goodsId);
        GoodsEntity goodsEntity = goodsResult.getData();
        detailDTO.setActTotalNum(actEntity.getActTotalNum());
        
        List<GoodsConsumerRelateEntity> consumerRelateEntityList = goodsConsumerRelateService.list(actId, goodsId);
        
        List<GoodsDetailWxDTO> goodsDetailWxDTOList = Lists.newArrayList();
        List<String> givingCodeList = Lists.newArrayList();
        for(GoodsConsumerRelateEntity temp : consumerRelateEntityList){
/*        	if(givingCodeCountMap.containsKey(temp.getOpenId())){
        		givingCodeCountMap.put(temp.getOpenId(),givingCodeCountMap.get(temp.getOpenId())+1);
        	}else {
        		givingCodeCountMap.put(temp.getOpenId(), 1);
        	}*/
        	if(openId.equals(temp.getOpenId())){
        		givingCodeList.add(temp.getConsumerGivingCode());
        	}
        	//get wxDetail
        	WxDetailEntity wxDetailEntity = wxDetailService.findByOpenId(openId);
        	GoodsDetailWxDTO goodsDetailWxDTO = new GoodsDetailWxDTO();
        	if(null != wxDetailEntity){
        		goodsDetailWxDTO.setCreateTime(temp.getCreateTime());
        		goodsDetailWxDTO.setWxDetailEntity(wxDetailEntity);
        		goodsDetailWxDTOList.add(goodsDetailWxDTO);
        	}
        }
        detailDTO.setGoodsDetailWxDTOList(goodsDetailWxDTOList);
        
        detailDTO.setGivingCodeList(givingCodeList);
        detailDTO.setGoodsDetail(goodsEntity.getGoodsDetail());
        detailDTO.setGoodsName(goodsEntity.getGoodsName());
        detailDTO.setGoodsTitle(goodsEntity.getGoodsTitle());
        detailDTO.setGoodsPrice(goodsEntity.getGoodsPrice());
        detailDTO.setParticipantsNum((consumerRelateEntityList.size()> actEntity.getActTotalNum())?actEntity.getActTotalNum(): consumerRelateEntityList.size() );
        detailDTO.setRemainingNum(
				(actEntity.getActTotalNum() - consumerRelateEntityList.size())<0?0:(actEntity.getActTotalNum() - consumerRelateEntityList.size()));
        modelAndView.addObject("detailDTO", detailDTO);
        modelAndView.addObject("actId", detailDTO.getActId());
        modelAndView.addObject("goodsId", detailDTO.getGoodsId());
        return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "createGoodsOrder")
    public String createGoodsOrder(String goodsId ) {
		//TODO get openId 添加事务
		String openId = "1";
		Result<GoodsEntity> goodsResult = goodsService.getById(Integer.valueOf(goodsId));
		GoodsEntity goodsEntity = goodsResult.getData();
		WxUserEntity wxUserEntity = wxUserService.getById(openId);
		if(goodsEntity.getJifen() > wxUserEntity.getJifen()){
			return "积分不足！";
		}
		
		//消费一个中奖号码
    	GoodsConsumerRelateEntity goodsConsumerRelateEntity = goodsConsumerRelateService.randomByGoodsId(Integer.valueOf(goodsId));
    	if(null == goodsConsumerRelateEntity){//中奖被消费完
    		return "你来晚了，该商品被抢购完";
    	}
    	
    	goodsConsumerRelateEntity.setOpenId(openId);
    	goodsConsumerRelateEntity.setIsUsed(true);
    	goodsConsumerRelateEntity.setGivingCodeSource("jifen");
    	int result = goodsConsumerRelateService.updateConsumer(goodsConsumerRelateEntity);
    	if(0 == result){//中奖被消费完
    		return "你来晚了，该商品被抢购完";
    	}
    	
		wxUserEntity.setJifen(wxUserEntity.getJifen() - goodsEntity.getJifen());
		wxUserService.updateJifen(wxUserEntity);
		
	  	OrderEntity orderEntity = new OrderEntity();
    	orderEntity.setGoodsId(Integer.valueOf(goodsId));
    	orderEntity.setGoodsNum(Integer.valueOf(1));
    	orderEntity.setOpenId(openId);
    	orderEntity.setSource("wxPayCode");
    	orderEntity.setStatus("yzf");
    	orderEntity.setType("jfPayCode");
    	//目前只支持一个一个购买
    	orderEntity.setPrice(goodsEntity.getGoodsPrice().subtract(new BigDecimal(Integer.valueOf(1))));
    	orderEntity.setOutTradeId(UUID.randomUUID().toString().replaceAll("-", ""));
    	orderService.save(orderEntity);
    	
		return "支付成功";
	}
}
