/**
 * 
 */
package com.common.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueProjectAdvert;
import com.common.system.service.ProjectAdvertService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Controller
@RequestMapping("projectAdvert")
public class ProjectAdvertController {
	@Resource
	private ProjectAdvertService projectAdvertService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/projectAdvert/list");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping("page")
	public PageBean<BlueProjectAdvert> findProjectList(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		return projectAdvertService.findProjectAdevertList(date,start, pageSize);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		return projectAdvertService.deleteProjectAdevert(id);
	}
	
	@RequestMapping(value = "view/{id}",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id,ModelAndView modelAndView){
        Result<BlueProjectAdvert> result = projectAdvertService.findProjectAdevert(id);
        modelAndView.addObject("bean",result.getData());
        modelAndView.setViewName("/system/admin/projectAdvert/view");
        return modelAndView;
    }
	
	/**
	 * 添加某条项目
	 * 
	 * @param file
	 *            图片
	 * @param context
	 *            介绍文本
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(ModelAndView modelAndView,HttpServletRequest request) {
		modelAndView.setViewName("/system/admin/projectAdvert/add");
		return modelAndView;
	}

	@RequestMapping(value = "save")
	public @ResponseBody
	Result<Integer> save(String title, String context,String jumpUrl,int type,
			@RequestParam("fileName") MultipartFile file) {
		return projectAdvertService.addProjectAdevert(title, context, jumpUrl, file,type);
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Integer id, ModelAndView modelAndView) {
		Result<BlueProjectAdvert> result = projectAdvertService.findProjectAdevert(id);
		modelAndView.addObject("bean", result.getData());
		modelAndView.addObject("url", "");
		modelAndView.setViewName("/system/admin/projectAdvert/edit");
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
	Result<Integer> updateAdvert(String title, String context,String jumpUrl,int type,
			int sid, @RequestParam("fileName") MultipartFile file) {
		return projectAdvertService.updateProjectAdevert(title, context, jumpUrl, sid, file, type);
	}

}
