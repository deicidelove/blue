/**
 * 
 */
package com.common.system.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueDoctorSchedule;
import com.common.system.entity.BluePation;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.DeptService;
import com.common.system.service.DoctorSchedulEService;
import com.common.system.service.DoctorService;
import com.common.system.service.PationService;
import com.common.system.util.DateUtil;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Controller
public class PationController {
	
	@Resource
	private CommonService commonService;

	@Resource
	private DoctorService doctorService;

	@Resource
	private DoctorSchedulEService doctorSchedulEService;

	@Resource
	private DeptService deptService;
	
	
	@Resource
	private PationService pationService;
	
	@RequestMapping(value = "selectPationPage/{scheduleId}", method = RequestMethod.GET)
	public ModelAndView selectPationPage(ModelAndView modelAndView, @PathVariable Integer scheduleId) throws ParseException {
//		BlueDoctorSchedule bds = doctorSchedulEService.findBySid(
//				scheduleId).getData();
//		BlueStaff staff = doctorService.findDoctor(bds.getStaffId()).getData();
//		BlueDept dept = deptService.findBySid(bds.getDeptId()).getData();
//		modelAndView.addObject("orderTime", DateUtil.formtString(bds.getCreateTime()));
//		modelAndView.addObject("bds", bds);
//		modelAndView.addObject("staff", staff);
//		modelAndView.addObject("dept", dept);
		Integer userId= 0;
		List<BluePation> pations = commonService.findPations(userId);
		
		modelAndView.addObject("pations", pations);
		modelAndView.addObject("scheduleId", scheduleId);
		
		modelAndView.setViewName("/html/pationList");
		return modelAndView;
	}
	
	@RequestMapping(value = "orderInfoPage/{sid}{scheduleId}", method = RequestMethod.GET)
	public ModelAndView orderInfoPage(ModelAndView modelAndView,  @PathVariable Integer sid,@PathVariable Integer scheduleId) throws ParseException {
		BlueDoctorSchedule bds = doctorSchedulEService.findBySid(
				scheduleId).getData();
		BlueStaff staff = doctorService.findDoctor(bds.getStaffId()).getData();
		BlueDept dept = deptService.findBySid(bds.getDeptId()).getData();
		modelAndView.addObject("orderTime", DateUtil.formtString(bds.getCreateTime()));
		modelAndView.addObject("bds", bds);
		modelAndView.addObject("staff", staff);
		modelAndView.addObject("dept", dept);
		BluePation pation = commonService.findPation(sid);
		
		modelAndView.addObject("pation", pation);
		
		modelAndView.setViewName("/html/pationList");
		return modelAndView;
	}
	
	@RequestMapping(value = "managePationPage", method = RequestMethod.GET)
	public ModelAndView managePationPage(ModelAndView modelAndView) throws ParseException {
		Integer userId= 0;
		List<BluePation> pations = commonService.findPations(userId);
		
		modelAndView.addObject("pations", pations);
		
		modelAndView.setViewName("/html/managePation");
		return modelAndView;
	}
	
	@RequestMapping(value = "updateIsDefault")
	@ResponseBody
	public Result<Integer> updateIsDefault(Integer sid,Integer isDefault) {
		Result<Integer> result = pationService.updatePationIsDefault(sid, isDefault);
		return result;

	}
	
	@RequestMapping(value = "deletePation")
	@ResponseBody
	public Result<Integer> deletePation(Integer sid) {
		Result<Integer> result = pationService.deletePation(sid);
		return result;

	}
	
	@RequestMapping(value = "editPationPage/{sid}", method = RequestMethod.GET)
	public ModelAndView editPationPage(ModelAndView modelAndView,  @PathVariable Integer sid){
		
		BluePation pation = commonService.findPation(sid);
		
		modelAndView.addObject("pation", pation);
		
		modelAndView.setViewName("/html/editPation");
		return modelAndView;
	}
	
	@RequestMapping(value = "updatePation")
	@ResponseBody
	public Result<Integer> updatePation(Integer sid,Integer isDefault,String name,String phone) {
		Result<Integer> result = pationService.updatePation(name, phone, sid, isDefault);
		return result;

	}
	
	@RequestMapping(value = "addPation")
	@ResponseBody
	public Result<Integer> addPation(Integer sid,Integer isDefault,String name,String phone) {
		Integer userId = 0;
		Result<Integer> result = pationService.addPation(name, phone, isDefault, userId);
		return result;

	}
	
	
	
	

}
