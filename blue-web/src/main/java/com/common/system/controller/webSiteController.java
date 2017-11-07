/**
 * 
 */
package com.common.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.dto.AdvertDto;
import com.common.system.entity.BlueAdvert;
import com.common.system.entity.GoodsEntity;
import com.common.system.service.CommonService;
import com.common.system.service.GoodsService;

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
	
	
	 @RequestMapping("/blueWebsite")
	    public ModelAndView greeting(ModelAndView modelAndView) {
		 List<GoodsEntity> goodsList = goodsService.list();
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
		 	goodsList = goodsList.size() >=3 ? goodsList.subList(0, 3):goodsList;
		 	for (GoodsEntity good : goodsList) {
				if(good.getGoodsPicUrl() == null){
					good.setGoodsPicUrl("");
				}
			}
		 	modelAndView.addObject("goodsList", goodsList);
	        modelAndView.addObject("adverts",dtos);
	        modelAndView.setViewName("/html/blueWebsite");
			return modelAndView;
	    }
}
