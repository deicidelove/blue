package com.common.system.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.mp.api.WxMpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.dto.ActGoodsDTO;
import com.common.system.dto.AdvertDto;
import com.common.system.dto.PersonalOrderDTO;
import com.common.system.entity.ActEntity;
import com.common.system.entity.BlueAdvert;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.entity.JifenLogEntity;
import com.common.system.entity.LastActEntity;
import com.common.system.entity.OrderEntity;
import com.common.system.entity.WxDetailEntity;
import com.common.system.entity.WxUserEntity;
import com.common.system.service.ActService;
import com.common.system.service.CommonService;
import com.common.system.service.GivingService;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsImgService;
import com.common.system.service.GoodsService;
import com.common.system.service.JifenLogService;
import com.common.system.service.LastActService;
import com.common.system.service.OrderService;
import com.common.system.service.WxDetailService;
import com.common.system.service.WxUserService;
import com.common.system.util.CookieUtil;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@RequestMapping(value = "lastact")
public class LastActController {

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
	
	@Resource
	private JifenLogService jifenLogService;
	
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private LastActService lastActService;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(LastActController.class);

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView,
			HttpServletRequest request) {
	 	List<BlueAdvert> result = commonService.findAdvert(1);
		List<AdvertDto> dtos = new ArrayList<>();
	 	for (int i=0;i<result.size();i=i+2) {
	 		AdvertDto dto = new AdvertDto();
	 		dto.setAdvert(result.get(i));
	 		if((i+1) < result.size()){
	 			dto.setAdvertNext(result.get(i+1));
	 		}else{
	 			dto.setFlag(false);
	 		}
	 		dtos.add(dto);
		}
	 	PageInfo<GoodsEntity> goodsPage = goodsService.listForPage(1, 10);
	 	List<GoodsEntity> goodsTempList = goodsPage.getList();
	 	
	 	List<ActGoodsDTO> actGoodsList = Lists.newArrayList();
	 	if(!CollectionUtils.isEmpty(goodsTempList)){
	 		Iterator<GoodsEntity> iterator = goodsTempList.iterator();
	 		while(iterator.hasNext()){
	 			GoodsEntity goods = iterator.next();
	 			List<GoodsImgEntity> imgList = goodsImgService.findByGoodsId(goods.getGoodsId(), "list_img");
	 			if(!CollectionUtils.isEmpty(imgList)){
	 				goods.setGoodsPicUrl(imgList.get(0).getGoodsImgUrl());
	 			}else{
	 				iterator.remove();
	 			}
	 			
	 		}
	 		goodsTempList = goodsTempList.size() >4 ? goodsTempList.subList(0, 4):goodsTempList;
	 		for(int i = 0; i< goodsTempList.size(); i++){
	 			if(i == 0){
	 				modelAndView.addObject("goods", goodsTempList.get(i));
	 				continue;
	 			}
	 			ActGoodsDTO actGoodsDTO = new ActGoodsDTO();
	 			GoodsEntity goodsEntity = goodsTempList.get(i);
	 			actGoodsDTO.setGoodsName(goodsEntity.getGoodsName());
	 			ActEntity actEntity = actService.getById(goodsEntity.getActId());
				actGoodsDTO.setActTotalNum(actEntity.getActTotalNum());
				actGoodsDTO.setActId(goodsEntity.getActId());
				actGoodsDTO.setGoodsId(goodsEntity.getGoodsId());
				actGoodsDTO.setGoodsName(goodsEntity.getGoodsName());
				actGoodsDTO.setGoodsPrice(goodsEntity.getGoodsPrice());
				actGoodsDTO.setJifen(null ==goodsEntity.getJifen()?0:goodsEntity.getJifen());
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
				actGoodsList.add(actGoodsDTO);
	 		}
	 	}
	 	modelAndView.addObject("actGoodsList", actGoodsList);
	 	//近期活动
	 	PageInfo<LastActEntity> lastActPage = lastActService.listForPage(1, 2);
	 	modelAndView.addObject("lastActList", lastActPage.getList());
        modelAndView.addObject("adverts",dtos);
        modelAndView.setViewName("/html/lastactfulicent");
		return modelAndView;
	}

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView order(ModelAndView modelAndView, Integer lastActId) {
		modelAndView.setViewName("/html/lastactdetail");
		Result<LastActEntity> lastactResult = lastActService.getById(lastActId);
		modelAndView.addObject("lastact",lastactResult.getData());
		return modelAndView;
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView lastActlist(ModelAndView modelAndView,
			HttpServletRequest request ,
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "50") int pageSize) {
		modelAndView.setViewName("/html/lastactlist");
		PageInfo<LastActEntity> lastActPage = lastActService.listForPage(start, pageSize);
		modelAndView.addObject("lastActList",lastActPage.getList());
		return modelAndView;
	}
	
	
	
}