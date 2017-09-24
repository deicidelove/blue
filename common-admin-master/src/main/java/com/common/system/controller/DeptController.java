/**
 * 
 */
package com.common.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.RcDept;
import com.common.system.service.DeptService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("dept")
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
	public PageBean<RcDept> findDepts(@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		return deptService.findDepts(start,pageSize);
	}

	@RequestMapping("/deleteDept")
	public Result<String> deleteDept(int sid) {
		return deptService.deleteDept(sid);
	}

	@RequestMapping("/findBySid")
	public Result<RcDept> findBySid(int sid) {
		return deptService.findBySid(sid);
	}

	@RequestMapping("/addDept")
	public Result<String> addDept(String name, String context) {
		return deptService.addDept(name, context);
	}

	@RequestMapping("/updateDept")
	public Result<String> updateDept(String name, String context, int sid) {
		return deptService.updateDept(name, context, sid);
	}

}
