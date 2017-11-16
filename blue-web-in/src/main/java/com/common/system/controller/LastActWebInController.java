package com.common.system.controller;

import java.text.ParseException;

import com.common.system.entity.LastActEntity;
import com.common.system.service.LastActService;
import com.common.system.util.ContextUtil;
import com.common.system.util.MsgCode;
import com.common.system.util.PageBean;
import com.common.system.util.PicUtil;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mr.Yangxiufeng on 2017/6/21.
 * Time:15:46
 * ProjectName:Common-admin
 */
@Controller
@RequestMapping(value = "lastact")
public class LastActWebInController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(LastActWebInController.class);
    
    @Autowired
    private LastActService lastActService;
    
    
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/lastact/list");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<LastActEntity> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        PageInfo<LastActEntity> pageInfo = lastActService.listForPage((start / pageSize) + 1, pageSize);
        return new PageBean<LastActEntity>(pageInfo);
    }
 
    @RequestMapping(value = "delete/{lastactId}",method = RequestMethod.GET)
    public @ResponseBody
    Result delete(@PathVariable Integer lastactId){
        Result<Integer> result = lastActService.deleteById(lastactId);
        return result;
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/lastact/add");
        return modelAndView;
    }
    @RequestMapping(value = "edit/{lastactId}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer lastactId,ModelAndView modelAndView){
        Result<LastActEntity> result = lastActService.getById(lastactId);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/system/admin/lastact/edit");
        return modelAndView;
    }
    @RequestMapping(value = "view/{lastactId}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer lastactId,ModelAndView modelAndView){
        Result<LastActEntity> result = lastActService.getById(lastactId);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/system/admin/lastact/view");
        return modelAndView;
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody Result<Integer> update( LastActEntity lastActEntity,@RequestParam("fileName") MultipartFile file) throws ParseException{
    	lastActEntity.setLastActContent(ContextUtil.setConent(lastActEntity.getLastActContent()));
    	String url = PicUtil.upFile(file);
    	lastActEntity.setLastActListImg(url);
        Result<LastActEntity> lastActResult = lastActService.getById(lastActEntity.getSid());
        Result<Integer> result = new Result<Integer>();
        LastActEntity lastAct = lastActResult.getData();
        lastAct.setLastActContent(lastActEntity.getLastActContent());
        result = lastActService.update(lastAct);
        return result;
    }
    @RequestMapping(value = "save")
    public @ResponseBody Result save(LastActEntity lastActEntity,@RequestParam("fileName") MultipartFile file){
    	Result<Integer> result  = new Result<Integer>();
    	try {
    		lastActEntity.setLastActContent(ContextUtil.setConent(lastActEntity.getLastActContent()));
    		String url = PicUtil.upFile(file);
        	lastActEntity.setLastActListImg(url);
    		lastActService.save(lastActEntity);
    		result.setStatus(true);
    		result.setCode(MsgCode.SUCCESS);
    		result.setMsg("成功！");
		} catch (Exception e) {
			LOGGER.error("系统异常，请联系管理员！",e);
			result.setStatus(false);
			result.setCode(MsgCode.FAILED);
			result.setMsg("系统异常，请联系管理员！");
		}
        return result;
    }
}
