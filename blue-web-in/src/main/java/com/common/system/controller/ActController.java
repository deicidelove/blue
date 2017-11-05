package com.common.system.controller;

import com.common.system.entity.ActEntity;
import com.common.system.entity.RcRole;
import com.common.system.entity.RcUser;
import com.common.system.service.ActService;
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
@RequestMapping(value = "act")
public class ActController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(ActController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ActService actService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/act/list");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<ActEntity> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo<ActEntity> pageInfo = actService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<ActEntity>(pageInfo);
    }

    @RequestMapping(value = "delete/{actId}",method = RequestMethod.GET)
    @ResponseBody
     public Result delete(@PathVariable Integer actId){
        Result<Integer> result = actService.deleteById(actId);
        return result;
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/act/add");
        return modelAndView;
    }
    @RequestMapping(value = "edit/{actId}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer actId,ModelAndView modelAndView){
        ActEntity result = actService.getById(actId);
        modelAndView.addObject("bean",result);
        modelAndView.setViewName("/system/admin/act/edit");
        return modelAndView;
    }
    @RequestMapping(value = "view/{actId}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer actId,ModelAndView modelAndView){
        ActEntity result = actService.getById(actId);
        modelAndView.addObject("bean",result);
        modelAndView.setViewName("/system/admin/act/view");
        return modelAndView;
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody Result update(Integer actId,String actName){
        Result<Integer> result = new Result<>();
        ActEntity actEntity = actService.getById(actId);
        actEntity.setActName(actName);
        result = actService.update(actEntity);
        return result;
    }
    @RequestMapping(value = "save")
    public @ResponseBody Result save(ActEntity actEntity){
        Result<Integer> result = actService.saveAct(actEntity);
        return result;
    }

    /**
     * <p>修改密码</p>
     * @param id
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @RequestMapping(value = "modifyPwd",method = RequestMethod.POST)
    public @ResponseBody Result update(Integer id,String oldPwd,String newPwd){
        Result result = new Result();
        if (StringUtils.isEmpty(newPwd)){
            result.setMsg("新密码不能为空");
            return result;
        }
        Result<RcUser> rcUserResult = userService.getById(id);
        RcUser user = rcUserResult.getData();
        String md5pwd = ShiroKit.md5(oldPwd,user.getSalt());
        if (!user.getPassword().equals(md5pwd)){
            result.setCode(MsgCode.FAILED);
            result.setStatus(false);
            result.setMsg("密码不正确");
            return result;
        }
        String salt = ShiroKit.getRandomSalt(5);
        String saltPwd = ShiroKit.md5(newPwd,salt);
        user.setPassword(saltPwd);
        user.setSalt(salt);
        try {
            userService.modifyPwd(user);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
