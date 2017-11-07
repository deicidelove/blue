/**
 * 
 */
package com.common.system.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueNeedWork;
import com.common.system.entity.BlueWantWork;
import com.common.system.service.NeedWorkService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("addOur")
public class AddOurController {

	@Resource
	private NeedWorkService needWorkService; 
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/addOur/list");
		return modelAndView;
	}
	@RequestMapping(value = "want", method = RequestMethod.GET)
	public ModelAndView wantList(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/addOur/want");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "page")
	public PageBean<BlueNeedWork> findWork(@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		return needWorkService.getNeedWorkList(null, date, start, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value = "wantPage")
	public PageBean<BlueWantWork> findaddWork(@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		return needWorkService.getWantWorkList(null, date, start, pageSize);
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		Result<Integer> result = needWorkService.deleteWork(id);
		return result;
	}
	@RequestMapping(value = "wantDelete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> wantDelete(@PathVariable Integer id) {
		Result<Integer> result = needWorkService.wantDeleteWork(id);
		return result;
	}
	
	 @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueNeedWork> result = needWorkService.findBySid(id);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/addOur/edit");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "add",method = RequestMethod.GET)
	    public ModelAndView add(ModelAndView modelAndView){
	        modelAndView.setViewName("/system/admin/addOur/add");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "save")
	    public @ResponseBody Result<Integer> save(String title,Integer needNum,String education,String experience,String wages,
				String workTime,String workAddress,String description,String requirement,String fringeBenefits){
	        return needWorkService.addWork(title, needNum, education, experience, wages, workTime, workAddress, description, requirement, fringeBenefits);
	    }
	 
	 @RequestMapping(value = "update",method = RequestMethod.POST)
	    public @ResponseBody Result<Integer> update(Integer sid,String title,Integer needNum,String education,String experience,String wages,
				String workTime,String workAddress,String description,String requirement,String fringeBenefits){	       
	        return needWorkService.updateWork(sid, title, needNum, education, experience, wages, workTime, workAddress, description, requirement, fringeBenefits);
	    }
	 
	 

	

}
