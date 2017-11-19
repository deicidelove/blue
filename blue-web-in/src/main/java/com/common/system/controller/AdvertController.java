/**
 * 
 */
package com.common.system.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueAdvert;
import com.common.system.entity.RcUser;
import com.common.system.service.AdvertService;
import com.common.system.service.CommonService;
import com.common.system.util.ContextUtil;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("advert")
public class AdvertController {

	@Resource
	private AdvertService advertService;
	
	@Resource CommonService commonService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/advert/list");
		return modelAndView;
	}

	/**
	 * 获取广告列表
	 * 
	 * @param type
	 *            广告/通知
	 * @return
	 */
	@ResponseBody
	@RequestMapping("page")
	public PageBean<BlueAdvert> getAdverList(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		if (StringUtils.isEmpty(search)) {
			search = "-1";
		}
		return advertService.getAdverList(Integer.valueOf(search), date, start,
				pageSize);
	}
	
	 @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
	    public ModelAndView view(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueAdvert> result = advertService.findAdvert(id);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/advert/view");
	        return modelAndView;
	    }

	/**
	 * 删除某条广告
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		Result<Integer> result = advertService.deleteAdver(id);
		return result;
	}

	/**
	 * 添加某条广告
	 * 
	 * @param type
	 *            广告/通知
	 * @param file
	 *            图片
	 * @param context
	 *            介绍文本
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/advert/add");
		return modelAndView;
	}

	@RequestMapping(value = "save")
	public @ResponseBody
	Result<Integer> save(int type, String context, String title,
			@RequestParam("fileName") MultipartFile file) {
		context = StringUtils.isEmpty(context)?"": ContextUtil.setConent(context);
		return advertService.addAdver(type, context, title, file);
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id, ModelAndView modelAndView) {
		Result<BlueAdvert> result = advertService.findAdvert(id);
		modelAndView.addObject("bean", result.getData());
		modelAndView.addObject("url", "");
		modelAndView.setViewName("/system/admin/advert/edit");
		return modelAndView;
	}

	/**
	 * 更新广告、通知
	 * 
	 * @param type
	 * @param context
	 * @param title
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody
	Result<Integer> updateAdvert(int type, String context, String title,
			@RequestParam("fileName") MultipartFile file, int sid) {
		context = StringUtils.isEmpty(context)?"": ContextUtil.setConent(context);
		return advertService.updateAdvert(type, context, title, file, sid);
	}
	
	@RequestMapping(value = "upFile", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView upPic(@RequestParam("fileName") MultipartFile file, int id) {
		String url =commonService.upFile(file, id, new BlueAdvert());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("url",url);
		modelAndView.setViewName("/system/admin/index");
		return modelAndView;
		
	}

}
