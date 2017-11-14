/**
 * 
 */
package com.common.system.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.dto.AdvertDto;
import com.common.system.entity.BlueAdvert;
import com.common.system.entity.BlueProjectAdvert;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.entity.LastActEntity;
import com.common.system.service.CommonService;
import com.common.system.service.GoodsImgService;
import com.common.system.service.GoodsService;
import com.common.system.service.LastActService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

/**
 * @author amkong
 *
 */
@Controller
public class webSiteController {
	
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private GoodsImgService goodsImgService;
	
	@Resource
	private LastActService lastActService;
	
	 @RequestMapping("/blueWebsite")
	    public ModelAndView greeting(ModelAndView modelAndView) {
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
		 	//蓝鲟官网-积分活动
		 	List<BlueProjectAdvert> gwJFHDList =commonService.findTypeAdvert(10);
		 	if(!CollectionUtils.isEmpty(gwJFHDList)){
		 		gwJFHDList = gwJFHDList.size()>=2?gwJFHDList.subList(0, 2) : gwJFHDList;
		 	}
		 	modelAndView.addObject("gwJFHDList", gwJFHDList);
		 	//蓝鲟官网-近期活动
		 	List<BlueProjectAdvert> gwZJHDList =commonService.findTypeAdvert(11);
		 	if(!CollectionUtils.isEmpty(gwZJHDList)){
		 		modelAndView.addObject("firstZJHD", gwZJHDList.get(0));
		 		gwZJHDList = gwZJHDList.size()>=4?gwZJHDList.subList(1, 4) : gwZJHDList;
		 	}
		 	modelAndView.addObject("gwZJHDList", gwZJHDList);
		 	//首页轮播图
		 	List<BlueProjectAdvert> webLBT = commonService.findTypeAdvert(0);
		 	//首页中间广告
		 	List<BlueProjectAdvert> webZJ = commonService.findTypeAdvert(1);
//		 	//首页近期活动广告
//		 	List<BlueProjectAdvert> webJQHD = commonService.findTypeAdvert(2);
	        modelAndView.addObject("adverts",dtos);
	        modelAndView.addObject("webLBT", webLBT);
	        modelAndView.addObject("webZJ", (webZJ==null||webZJ.size() <=0) ? null : webZJ.get(0));
//	        try {
//	        	 modelAndView.addObject("webJQHD0", webJQHD.get(0));
//	        	 modelAndView.addObject("webJQHD1", webJQHD.get(1));
//	        	 modelAndView.addObject("webJQHD2", webJQHD.get(2));
//	        	 modelAndView.addObject("webJQHD3", webJQHD.get(3));
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
	        modelAndView.setViewName("/html/blueWebsite");
			return modelAndView;
	    }
}
