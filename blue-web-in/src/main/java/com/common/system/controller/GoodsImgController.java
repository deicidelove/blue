package com.common.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.GoodsEntity;
import com.common.system.service.GoodsImgService;
import com.common.system.util.PageBean;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "goodsimg")
public class GoodsImgController {

	@Resource
	private GoodsImgService goodsImgService;
	
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/goods/list");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<GoodsEntity> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo<GoodsEntity> pageInfo = null;
        		goodsImgService.findByGoodsId(null,null);
        return new PageBean<GoodsEntity>(pageInfo);
    }
}
