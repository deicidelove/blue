package com.common.system.controller;

import java.text.ParseException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.GoodsEntity;
import com.common.system.entity.GoodsImgEntity;
import com.common.system.service.GoodsImgService;
import com.common.system.service.GoodsService;
import com.common.system.util.MsgCode;
import com.common.system.util.PageBean;
import com.common.system.util.PicUtil;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "goodsimg")
public class GoodsImgController {

	@Resource
	private GoodsImgService goodsImgService;
	
	@Resource
	private GoodsService goodsService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsImgController.class);
	
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/goodsimg/list");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<GoodsImgEntity> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "length", defaultValue = "10") int pageSize, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "search", required = false) String search) {
        Integer goodsId = StringUtils.isNotBlank(search)? Integer.valueOf(search): null;
    	PageInfo<GoodsImgEntity> pageInfo = goodsImgService.listForPage((start / pageSize) + 1, pageSize, goodsId);
        return new PageBean<GoodsImgEntity>(pageInfo);
    }
    
    @RequestMapping(value = "goodsimgupload",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName("/system/admin/goodsimg/add");
        return modelAndView;
    }
    
    @ResponseBody
	@RequestMapping(value = "save")
	public Result<Integer> save(GoodsImgEntity goodsImgEntity , @RequestParam("fileName") MultipartFile file) {
    	Result<Integer> result  = new Result<Integer>();
    	try {
    		Result<GoodsEntity> goodsEntity = goodsService.getById(goodsImgEntity.getGoodsId());
    		if(null == goodsEntity.getData()){
    			result.setStatus(false);
    			result.setCode(MsgCode.FAILED);
    			result.setMsg("未查到相关商品Id，无法保存！");
    			return result;
    		}
			String urlTream =PicUtil.upFile(file);
			goodsImgEntity.setGoodsImgUrl(urlTream);
			goodsImgService.save(goodsImgEntity);
			result.setStatus(true);
			result.setCode(MsgCode.SUCCESS);
			result.setMsg("操作成功！");
		} catch (ParseException e) {
			LOGGER.error("系统异常，请联系管理员！",e);
			result.setStatus(false);
			result.setCode(MsgCode.FAILED);
			result.setMsg("系统异常，请联系管理员！");
		}
		return result;
	}
    
    @RequestMapping(value = "view/{goodsImgId}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer goodsImgId,ModelAndView modelAndView){
        GoodsImgEntity result = goodsImgService.findById(goodsImgId);
        modelAndView.addObject("bean",result);
        modelAndView.setViewName("/system/admin/goodsimg/view");
        return modelAndView;
    }
    
    @RequestMapping(value = "delete/{goodsImgId}",method = RequestMethod.GET)
    public @ResponseBody
    Result delete(@PathVariable Integer goodsImgId){
        Result<Integer> result = goodsImgService.delete(goodsImgId);
        return result;
    }
}
