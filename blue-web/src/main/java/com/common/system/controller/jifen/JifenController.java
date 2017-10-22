package com.common.system.controller.jifen;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.dto.ActGoodsDTO;
import com.common.system.entity.ActEntity;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.common.system.entity.GoodsEntity;
import com.common.system.service.ActService;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsService;
import com.google.common.collect.Lists;

@RestController
@RequestMapping(value = "jifen")
public class JifenController {
	
	@Resource
	private ActService actService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private GoodsConsumerRelateService goodsConsumerRelateService;
	
	@RequestMapping(value = "index",method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("/jifen/act");
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
			ActEntity actEntity = actService.getById(goodsEntity.getActId());
			ActGoodsDTO actGoodsDTO = new ActGoodsDTO();
			actGoodsDTO.setActTotalNum(actEntity.getActTotalNum());
			actGoodsDTO.setActId(goodsEntity.getActId());
			actGoodsDTO.setGoodsId(goodsEntity.getGoodsId());
			actGoodsDTO.setGoodsName(goodsEntity.getGoodsName());
			actGoodsDTO.setGoodsPrice(goodsEntity.getGoodsPrice());
			actGoodsDTO.setGoodsTitle(goodsEntity.getGoodsTitle());
			List<GoodsConsumerRelateEntity> goodsConsumerRelateList = goodsConsumerRelateService.list(goodsEntity.getActId(), goodsEntity.getGoodsId());
			actGoodsDTO.setRemainingNum(actEntity.getActTotalNum() - goodsConsumerRelateList.size());
			resultList.add(actGoodsDTO);
		}
        return resultList;
    }
	
	@RequestMapping(value = "gotoactdetail",method = RequestMethod.GET)
	public ModelAndView gotoactdetail(ModelAndView modelAndView){
        modelAndView.setViewName("/jifen/actDetail");
        return modelAndView;
	}
	
}
