package com.common.system.controller;

import java.util.List;
import java.util.Map;

import com.common.system.entity.ActEntity;
import com.common.system.entity.GivingEntity;
import com.common.system.entity.GoodsEntity;
import com.common.system.entity.WxDetailEntity;
import com.common.system.service.ActService;
import com.common.system.service.GivingService;
import com.common.system.service.GoodsConsumerRelateService;
import com.common.system.service.GoodsService;
import com.common.system.service.RoleService;
import com.common.system.service.UserService;
import com.common.system.service.WxDetailService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mr.Yangxiufeng on 2017/6/21.
 * Time:15:46
 * ProjectName:Common-admin
 */
@Controller
@RequestMapping(value = "goodsconsumerrelate")
public class GoodsConsumerRelateController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsConsumerRelateController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private GoodsConsumerRelateService goodsConsumerRelateService;
    
    @Autowired
    private GivingService givingService;
    
    @Autowired
    private ActService actService;
    
    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private WxDetailService wxDetailService;
    
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/goodsconsumerrelate/list");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<Map<String, Object>> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String,Object>>();
        PageInfo<GivingEntity> givingEntityPage = givingService.listForPage((start / pageSize) + 1, pageSize);
        if(CollectionUtils.isEmpty(givingEntityPage.getList())){
        	
        	return new PageBean<Map<String, Object>>(pageInfo);
        }else{
        	List<Map<String, Object>> list = Lists.newArrayList();
        	for(GivingEntity givingEntity : givingEntityPage.getList()){
        		Map<String, Object > givingMap = Maps.newHashMap();
        		givingMap.put("actId", String.valueOf(givingEntity.getActId()));
        		ActEntity actEntity = actService.getById(givingEntity.getActId());
        		if(null != actEntity){
        			givingMap.put("actName", actEntity.getActName());
        			givingMap.put("actPeriods", actEntity.getActPeriods());
        		}
        		givingMap.put("goodsId", String.valueOf(givingEntity.getGoodsId()));
        		Result<GoodsEntity> goodsResult =goodsService.getById(givingEntity.getGoodsId());
        		if(null != goodsResult.getData()){
        			givingMap.put("goodsName", goodsResult.getData().getGoodsName());
        		}
        		givingMap.put("goodsId", givingEntity.getOpenId());
        		WxDetailEntity wxDetailEntity = wxDetailService.findByOpenId(givingEntity.getOpenId());
        		if(null != wxDetailEntity){
        			givingMap.put("name", wxDetailEntity.getName());
        		}
        		givingMap.put("givingCode", givingEntity.getGivingCode());
        		givingMap.put("createTime", givingEntity.getCreateTime());
        		list.add(givingMap);
        	}
        	pageInfo.setList(list);
        	return new PageBean<Map<String, Object>>(pageInfo);
        }
    }
 
}
