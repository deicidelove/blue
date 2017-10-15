/**
 * 
 */
package com.common.system.controller;

import java.util.List;

import javax.annotation.Resource;

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
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueSource;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.SourceService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("source")
public class SourceController {

	@Resource
	private SourceService sourceService;
	@Resource
	private CommonService commonService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		List<BlueDept> depts = commonService.findDept();
		modelAndView.addObject("depts", depts);
		modelAndView.setViewName("/system/admin/source/list");
		return modelAndView;
	}


	@ResponseBody
	@RequestMapping(value = "page")
	public PageBean<BlueSource> findSourceByDept(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		if(StringUtils.isEmpty(search)){
			search = "-1";
		}
		return sourceService.findSourceByDept(date,Integer.valueOf(search), start,
				pageSize);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		Result<Integer> result = sourceService.deleteSource(id);
		return result;
	}
	
	@RequestMapping(value = "add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView){
		 List<BlueDept> depts = commonService.findDept();
		modelAndView.addObject("depts", depts);
        modelAndView.setViewName("/system/admin/source/add");
        return modelAndView;
    }
 
	@ResponseBody
	@RequestMapping(value = "save")
	public Result<Integer> addSource(int deptId, String context,
			String title,@RequestParam("fileName") MultipartFile file) {
		return sourceService.addSource(deptId, 18, context, title,file);
	}

	 @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueSource> result = sourceService.findSource(id);
	        List<BlueDept> depts = commonService.findDept();
			modelAndView.addObject("depts", depts);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/source/edit");
	        return modelAndView;
	    }
	 
	 @ResponseBody
	 @RequestMapping(value = "update",method = RequestMethod.POST)
	public Result<Integer> updateSource(int sid, int deptId,
			String context, String title,@RequestParam("fileName") MultipartFile file) {
		return sourceService.updateSource(sid, deptId,18, context,
				title,file);
	}

	 @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
	    public ModelAndView view(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueSource> result = sourceService.findSource(id);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/source/view");
	        return modelAndView;
	    }

}
