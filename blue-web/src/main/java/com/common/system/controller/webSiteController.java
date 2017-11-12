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
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.service.CommonService;
import com.common.system.service.GoodsImgService;
import com.common.system.service.GoodsService;
import com.github.pagehelper.PageInfo;

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
		 	PageInfo<GoodsEntity> goodsPage = goodsService.listForPage(1, 10);
		 	List<GoodsEntity> goodsList = goodsPage.getList();
		 	if(!CollectionUtils.isEmpty(goodsList)){
		 		Iterator<GoodsEntity> iterator = goodsList.iterator();
		 		while(iterator.hasNext()){
		 			GoodsEntity goods = iterator.next();
		 			List<GoodsImgEntity> imgList = goodsImgService.findByGoodsId(goods.getGoodsId(), "list_img");
		 			if(!CollectionUtils.isEmpty(imgList)){
		 				goods.setGoodsPicUrl(imgList.get(0).getGoodsImgUrl());
		 			}else{
		 				iterator.remove();
		 			}
		 			
		 		}
		 	}

		 	goodsList = goodsList.size() >4 ? goodsList.subList(0, 4):goodsList;
		 	modelAndView.addObject("goodsList", goodsList);
	        modelAndView.addObject("adverts",dtos);
	        modelAndView.setViewName("/html/blueWebsite");
			return modelAndView;
	    }
}
