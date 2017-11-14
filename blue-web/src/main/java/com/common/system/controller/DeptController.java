/**
 * 
 */
package com.common.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueProjectAdvert;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.DeptService;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
public class DeptController {
	@Resource
	private CommonService commonService;
	
	@Resource
	private DeptService deptService;
	

	@RequestMapping("/deptIntroducePage")
	public ModelAndView deptListPage(ModelAndView modelAndView) {
		//科室轮播图
	 	List<BlueProjectAdvert> deptLBT = commonService.findTypeAdvert(9);
		List<BlueDept> depts = commonService.findDept();
		modelAndView.addObject("depts", depts);
		modelAndView.addObject("deptLBT", deptLBT);
		modelAndView.setViewName("/html/deptList");
		return modelAndView;
	}

	@RequestMapping(value = "deptDetial/{sid}", method = RequestMethod.GET)
	public ModelAndView deptDetialById(ModelAndView modelAndView,
			@PathVariable Integer sid) {
		List<BlueProjectAdvert> deptLBT = commonService.findTypeAdvert(9);
		Result<BlueDept> result = deptService.findBySid(sid);
		List<BlueStaff> staffs = commonService.findStaffByDeptId(sid);
        modelAndView.addObject("dept",result.getData());
        modelAndView.addObject("doctors",staffs);
        modelAndView.addObject("deptLBT", deptLBT);
        modelAndView.setViewName("/html/deptDetial");
        return modelAndView;

	}

}
