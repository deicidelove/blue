/**
 * 
 */
package com.common.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.dto.DoctorDto;
import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueDeptDoctorPic;
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
//		List<BlueStaff> staffs =  commonService.findStaff();
		modelAndView.addObject("depts", depts);
		modelAndView.addObject("flag", true);
		List<DoctorDto> doctors = new ArrayList<>();
		
		for (BlueDept dept : depts) {
			DoctorDto dto = new DoctorDto();
			BlueDeptDoctorPic url = commonService.findPic(0,dept.getSid());
			List<BlueStaff> staffs = commonService.findStaffByDeptId(dept.getSid());
			dto.setDoctors(staffs);
			dto.setUrl(url==null?"":url.getUrl());
			doctors.add(dto);
		}
		modelAndView.addObject("doctors", doctors);
		modelAndView.setViewName("/html/doctor");
		return modelAndView;
	}
	
	@RequestMapping(value = "findDoctor/{sid}", method = RequestMethod.GET)
	public ModelAndView doctorPage(ModelAndView modelAndView,
			@PathVariable Integer sid) {
		List<BlueDept> depts = commonService.findDept();
		List<BlueStaff> staffs = commonService.findStaffByDeptId(sid);
		BlueDeptDoctorPic url = commonService.findPic(0,sid);
		List<BlueDept> newDepts = new ArrayList<>();
		for (BlueDept blueDept : depts) {
			if(blueDept.getSid().equals(sid)){
				newDepts.add(blueDept);
				depts.remove(blueDept);
				break;
			}
		}
		newDepts.addAll(depts);
		List<DoctorDto> doctors = new ArrayList<>();
		DoctorDto dto = new DoctorDto();
		dto.setDoctors(staffs);
		dto.setUrl(url.getUrl());
		doctors.add(dto);
		modelAndView.addObject("deptName", newDepts!=null?newDepts.get(0).getName():"");
		modelAndView.addObject("flag", false);
		modelAndView.addObject("depts", newDepts);
		modelAndView.addObject("doctors", doctors);
		modelAndView.setViewName("/html/doctor");
		return modelAndView;
	}
	
	@RequestMapping(value = "doctorFindBySearch/{search}", method = RequestMethod.GET)
	public ModelAndView doctorFindBySearch(ModelAndView modelAndView,@PathVariable String search){
		List<BlueDept> depts = commonService.findDept();
		List<BlueStaff> staffs = commonService.findStaffBySearch(search);
		List<Integer> deptIds = new ArrayList<>();
		for (BlueStaff staff : staffs) {
			deptIds.add(staff.getDeptId());
		}
		List<DoctorDto> doctors = new ArrayList<>();
		
		for (Integer deptId : deptIds) {
			DoctorDto dto = new DoctorDto();
			BlueDeptDoctorPic url = commonService.findPic(0,deptId);
			List<BlueStaff> staffTemp = commonService.findStaffByDeptId(deptId);
			dto.setDoctors(staffTemp);
			dto.setUrl(url.getUrl());
			doctors.add(dto);
		}
		modelAndView.addObject("doctors", doctors);
		modelAndView.addObject("search", search);
		modelAndView.addObject("flag", true);
		modelAndView.addObject("depts", depts);
		modelAndView.setViewName("/html/doctor");
		return modelAndView;
	}

}
