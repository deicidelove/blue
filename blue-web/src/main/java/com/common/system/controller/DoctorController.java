/**
 * 
 */
package com.common.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueProject;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;

/**
 * @author amkong
 * 
 */

@Controller
public class DoctorController {
	@Resource
	private CommonService commonService;

	@RequestMapping("/doctorPage")
	public ModelAndView doctorPage(ModelAndView modelAndView) {
		List<BlueDept> depts = commonService.findDept();
		List<BlueStaff> staffs =  commonService.findStaff();
		modelAndView.addObject("depts", depts);
		modelAndView.addObject("flag", true);
		modelAndView.addObject("doctors", staffs);
		modelAndView.setViewName("/html/doctor");
		return modelAndView;
	}
	
	@RequestMapping("/findDoctor/{sid}")
	public ModelAndView doctorPage(ModelAndView modelAndView,
			Integer sid) {
		List<BlueDept> depts = commonService.findDept();
		List<BlueStaff> staffs = commonService.findStaffByDeptId(sid);
		List<BlueDept> newDepts = new ArrayList<>();
		for (BlueDept blueDept : depts) {
			if(blueDept.getSid().equals(sid)){
				newDepts.add(blueDept);
				depts.remove(blueDept);
				break;
			}
		}
		newDepts.addAll(depts);
		modelAndView.addObject("flag", false);
		modelAndView.addObject("depts", newDepts);
		modelAndView.addObject("doctors", staffs);
		modelAndView.setViewName("/html/doctor");
		return modelAndView;
	}

}
