package com.common.system.controller.jifen;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.common.system.entity.GivingEntity;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.entity.OrderEntity;
import com.common.system.entity.WxDetailEntity;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.ActService;
import com.common.system.service.GivingService;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsImgService;
import com.common.system.service.GoodsService;
import com.common.system.service.OrderService;
import com.common.system.service.WxDetailService;
import com.common.system.service.WxUserService;
import com.common.system.util.CookieUtil;
import com.common.system.util.Result;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
	
	@Resource
	private GivingService givingService;
    
	private static final Logger LOG = LoggerFactory.getLogger(JifenController.class);
	
	@RequestMapping(value = "index",method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("/jifen/act");
       /* try {
        	if(StringUtils.isNoneBlank(code)){
        		WxMpOAuth2AccessToken token = wxService.oauth2getAccessToken(code);
        		System.out.println(token.getOpenId());
        	}
		} catch (WxErrorException e) {
			LOG.error("	");
		}*/
        String openId = CookieUtil.getCookieValue(request, "openId");
        //TODO
        openId = "1";
        WxUserEntity wxUserEntity = wxUserService.getById(openId);
        modelAndView.addObject("wxUserEntity", wxUserEntity);
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
			
			List<GoodsImgEntity> imgEntityList = goodsImgService.findByGoodsId(goodsEntity.getGoodsId(), "list_img");
			if(!CollectionUtils.isEmpty(imgEntityList)){
				actGoodsDTO.setListImg(imgEntityList.get(0).getGoodsImgUrl());
			}
			resultList.add(actGoodsDTO);
		}
        return resultList;
    }
	
	@RequestMapping(value = "actdetail",method = RequestMethod.GET)
	public ModelAndView actdetail(ModelAndView modelAndView, HttpServletRequest request, Integer actId, Integer goodsId){
		
		String openId = CookieUtil.getCookieValue(request, "openId");
		
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
        	if(StringUtils.isNotBlank(openId) && openId.equals(temp.getOpenId())){
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
        List<GoodsImgEntity> goodsImgEntityList = goodsImgService.findByGoodsId(goodsId, "ad_img");
        if(!CollectionUtils.isEmpty(goodsImgEntityList)){
        	detailDTO.setImgList(goodsImgEntityList);
        }
        modelAndView.addObject("detailDTO", detailDTO);
        modelAndView.addObject("actId", detailDTO.getActId());
        modelAndView.addObject("goodsId", detailDTO.getGoodsId());
        return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "createGoodsOrder")
    public Map<String, String > createGoodsOrder(String goodsId ,HttpServletRequest request ) {
		Map<String, String > resultMap  = Maps.newHashMap();
		//TODO get openId 添加事务
		String openId = CookieUtil.getCookieValue(request, "openId");
		
		//TODO
		openId = "1";
		
		Result<GoodsEntity> goodsResult = goodsService.getById(Integer.valueOf(goodsId));
		GoodsEntity goodsEntity = goodsResult.getData();
		WxUserEntity wxUserEntity = wxUserService.getById(openId);
		if(goodsEntity.getJifen() > wxUserEntity.getJifen()){
			resultMap.put("status", "fail");
			resultMap.put("message", "积分不足！");
			return resultMap;
		}
		
		//消费一个中奖号码
    	GoodsConsumerRelateEntity goodsConsumerRelateEntity = goodsConsumerRelateService.randomByGoodsId(Integer.valueOf(goodsId));
    	if(null == goodsConsumerRelateEntity){//中奖被消费完
			resultMap.put("status", "fail");
    		resultMap.put("message", "你来晚了，该商品被抢购完");
			return resultMap;
    	}
    	
    	goodsConsumerRelateEntity.setOpenId(openId);
    	goodsConsumerRelateEntity.setIsUsed(true);
    	goodsConsumerRelateEntity.setGivingCodeSource("jifen");
    	int result = goodsConsumerRelateService.updateConsumer(goodsConsumerRelateEntity);
    	if(0 == result){//中奖被消费完
			resultMap.put("status", "fail");
    		resultMap.put("message", "你来晚了，该商品被抢购完");
			return resultMap;
    	}
    	
		wxUserEntity.setJifen(wxUserEntity.getJifen() - goodsEntity.getJifen());
		wxUserService.updateJifen(wxUserEntity);
		
	  	OrderEntity orderEntity = new OrderEntity();
    	orderEntity.setGoodsId(Integer.valueOf(goodsId));
    	orderEntity.setGoodsNum(Integer.valueOf(1));
    	orderEntity.setOpenId(openId);
    	orderEntity.setSource("jfPayCode");
    	orderEntity.setStatus("yzf");
    	orderEntity.setType("jfPayCode");
    	//目前只支持一个一个购买
    	orderEntity.setPrice(goodsEntity.getGoodsPrice().subtract(new BigDecimal(Integer.valueOf(1))));
    	orderEntity.setOutTradeId(UUID.randomUUID().toString().replaceAll("-", ""));
    	orderService.save(orderEntity);
		resultMap.put("status", "success");
		resultMap.put("message", "支付成功");
		return resultMap;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "updateShowTip")
    public String updateShowTip(String goodsId ,HttpServletRequest request ) {
		//TODO get openId 添加事务
		String openId = CookieUtil.getCookieValue(request, "openId");
		try {
			wxUserService.updateTip(openId, false);
		} catch (Exception e) {
			LOG.error("updateShowTip error!", e);
			return "failure";
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "overgiving")
    public List<Map<String, Object>> overGiving(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "100") int pageSize ) {
		//TODO get openId 添加事务
		List<Map<String, Object>> resultList = Lists.newArrayList();
		try {
			List<GivingEntity> givingList = givingService.list();
			for(GivingEntity givingEntity : givingList){
				Map<String, Object> map = Maps.newHashMap();
				map.put("givingCode", givingEntity.getGivingCode());
				map.put("createTime", givingEntity.getCreateTime());
				ActEntity actEntity = actService.getById(givingEntity.getActId());
				if(null != actEntity){
					map.put("actPeriods", actEntity.getActPeriods());
					map.put("actTotalNum", actEntity.getActTotalNum());
				}
				
				Result<GoodsEntity> goodsResult = goodsService.getById(givingEntity.getGoodsId());
				GoodsEntity goodsEntity = goodsResult.getData();
				map.put("goodsName", goodsEntity.getGoodsName());
				List<GoodsImgEntity> goodsImgEntityList = goodsImgService.findByGoodsId(givingEntity.getGoodsId(), "list_img");
				if(!CollectionUtils.isEmpty(goodsImgEntityList) && goodsImgEntityList.size() > 0 ){
					map.put("goodsImgUrl", goodsImgEntityList.get(0).getGoodsImgUrl());
				}
				
				WxDetailEntity wxUserEntity = wxDetailService.findByOpenId(givingEntity.getOpenId()) ;
				if(null != wxUserEntity){
					map.put("name", wxUserEntity.getName());
				}
				resultList.add(map);
			}
			
		} catch (Exception e) {
			LOG.error("overGiving error!", e);
		}
		return resultList;
	}
}
