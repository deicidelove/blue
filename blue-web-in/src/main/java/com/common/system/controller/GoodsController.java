package com.common.system.controller;

import com.common.system.dto.GoodsConsumerRelateDTO;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.RcRole;
import com.common.system.entity.RcUser;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsService;
import com.common.system.service.RoleService;
import com.common.system.service.UserService;
import com.common.system.shiro.ShiroKit;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/6/21.
 * Time:15:46
 * ProjectName:Common-admin
 */
@Controller
@RequestMapping(value = "goods")
public class GoodsController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
    
    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/goods/list");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<GoodsEntity> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo<GoodsEntity> pageInfo = goodsService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<GoodsEntity>(pageInfo);
    }
 
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public @ResponseBody
    Result delete(@PathVariable Integer id){
        Result<Integer> result = goodsService.deleteById(id);
        return result;
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/goods/add");
        return modelAndView;
    }
    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
        Result<GoodsEntity> result = goodsService.getById(id);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/system/admin/user/edit");
        return modelAndView;
    }
    @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id,ModelAndView modelAndView){
        Result<GoodsEntity> result = goodsService.getById(id);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/system/admin/user/view");
        return modelAndView;
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody Result update(Integer goodsId, String name){
        Result<GoodsEntity> goodsResult = goodsService.getById(goodsId);
        Result<Integer> result = new Result<>();
        if (result.isStatus()){
        	GoodsEntity goods = goodsResult.getData();
 
            result = goodsService.update(goods);
        }
        return result;
    }
    @RequestMapping(value = "save")
    public @ResponseBody Result save(GoodsEntity goodsEntity, @RequestParam(value = "role", required = false) Integer roleId){
    	goodsEntity.setCreateTime(new Date());
        Result<Integer> result = goodsService.saveGoods(goodsEntity);
        return result;
    }
}
