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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueDept;
import com.common.system.service.DeptService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("blueDept")
public class DeptController {

	@Resource
	private DeptService deptService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/dept/list");
		return modelAndView;
	}

	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "page")
	public PageBean<BlueDept> findDepts(@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		return deptService.findDepts(start,pageSize);
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		Result<Integer> result = deptService.deleteDept(id);
		return result;
	}
	
	 @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueDept> result = deptService.findBySid(id);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/dept/edit");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "add",method = RequestMethod.GET)
	    public ModelAndView add(ModelAndView modelAndView){
	        modelAndView.setViewName("/system/admin/dept/add");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "save")
	    public @ResponseBody Result<Integer> save(String deptName,String context,@RequestParam("fileName") MultipartFile file){
	        return deptService.addDept(deptName, context,file);
	    }
	 
	 @RequestMapping(value = "update",method = RequestMethod.POST)
	    public @ResponseBody Result<Integer> update(String deptName,String context, int sid,@RequestParam("fileName") MultipartFile file){	       
	        return deptService.updateDept(deptName, context, sid,file);
	    }

}
