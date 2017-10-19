package com.common.system.controller.jifen;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.ActEntity;
import com.common.system.service.ActService;
import com.common.system.service.GoodsService;

@RestController
@RequestMapping(value = "jifen")
public class JifenController {
	
	@Resource
	private ActService actService;
	
	@Resource
	private GoodsService goodsService;
	
	@RequestMapping(value = "index",method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("/jifen/act");
        return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "getactlist")
    public List<ActEntity> getactlist(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
		List<ActEntity> resultList = actService.list();
        return resultList;
    }
	
	@RequestMapping(value = "gotoactdetail",method = RequestMethod.GET)
	public ModelAndView gotoactdetail(ModelAndView modelAndView){
        modelAndView.setViewName("/jifen/actDetail");
        return modelAndView;
	}
	
}
