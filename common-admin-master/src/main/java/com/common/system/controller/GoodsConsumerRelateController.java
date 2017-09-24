package com.common.system.controller;

import com.common.system.dto.GoodsConsumerRelateDTO;
import com.common.system.entity.ActEntity;
import com.common.system.entity.RcRole;
import com.common.system.entity.RcUser;
import com.common.system.service.ActService;
import com.common.system.service.GoodsConsumerRelateService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

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
    
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/goodsconsumerrelate/list");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<GoodsConsumerRelateDTO> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo<GoodsConsumerRelateDTO> pageInfo = goodsConsumerRelateService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<GoodsConsumerRelateDTO>(pageInfo);
    }

}
