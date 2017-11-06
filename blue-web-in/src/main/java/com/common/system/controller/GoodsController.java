package com.common.system.controller;

import com.common.system.dto.GoodsConsumerRelateDTO;
import com.common.system.entity.ActEntity;
import com.common.system.entity.GoodsConsumerRelateEntity;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.RcRole;
import com.common.system.entity.RcUser;
import com.common.system.service.ActService;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsService;
import com.common.system.service.RoleService;
import com.common.system.service.UserService;
import com.common.system.shiro.ShiroKit;
import com.common.system.util.MsgCode;
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
    
    @Autowired
    private ActService actService;
    
    @Autowired
    private GoodsConsumerRelateService goodsConsumerRelateService;
    
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
 
    @RequestMapping(value = "delete/{goodsId}",method = RequestMethod.GET)
    public @ResponseBody
    Result delete(@PathVariable Integer goodsId){
        Result<Integer> result = goodsService.deleteById(goodsId);
        return result;
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/goods/add");
        return modelAndView;
    }
    @RequestMapping(value = "edit/{goodsId}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer goodsId,ModelAndView modelAndView){
        Result<GoodsEntity> result = goodsService.getById(goodsId);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/system/admin/goods/edit");
        return modelAndView;
    }
    @RequestMapping(value = "view/{goodsId}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer goodsId,ModelAndView modelAndView){
        Result<GoodsEntity> result = goodsService.getById(goodsId);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/system/admin/goods/view");
        return modelAndView;
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody Result<Integer> update( GoodsEntity goodsEntity){
        Result<GoodsEntity> goodsResult = goodsService.getById(goodsEntity.getGoodsId());
        Result<Integer> result = new Result<Integer>();
        GoodsEntity goods = goodsResult.getData();
//        goods.setGoodsName(goodsEntity.getGoodsName());
        goods.setGoodsTitle(goodsEntity.getGoodsTitle());
        goods.setGoodsDetail(goodsEntity.getGoodsDetail());
        result = goodsService.update(goods);
        return result;
    }
    @RequestMapping(value = "save")
    public @ResponseBody Result save(GoodsEntity goodsEntity){
    	Result<Integer> result  = new Result<Integer>();
    	try {
    		ActEntity actEntity = actService.getById(goodsEntity.getActId());
    		if(null == actEntity){
    			result.setStatus(false);
    			result.setCode(MsgCode.FAILED);
    			result.setMsg("未查到相关互动Id，无法保存！");
    			return result;
    		}
    		result = goodsService.saveGoods(goodsEntity);
    		if(result.isStatus()){
    			for(int i = 0; i < actEntity.getActTotalNum(); i++){
    				GoodsConsumerRelateEntity goodsConsumerRelateEntity = new GoodsConsumerRelateEntity();
    				goodsConsumerRelateEntity.setActId(actEntity.getActId());
    				goodsConsumerRelateEntity.setConsumerGivingCode("10000"+i);
    				goodsConsumerRelateEntity.setGoodsId(result.getData());
    				goodsConsumerRelateService.saveGoodsConsumerRelate(goodsConsumerRelateEntity);
    			}
    		}
		} catch (Exception e) {
			LOGGER.error("系统异常，请联系管理员！",e);
			result.setStatus(false);
			result.setCode(MsgCode.FAILED);
			result.setMsg("系统异常，请联系管理员！");
		}
        
        return result;
    }
}
