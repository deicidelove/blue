/**
 * 
 */
package com.common.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueHospital;
import com.common.system.service.CommonService;
import com.common.system.service.HospitalService;

/**
 * @author amkong
 *
 */
@Controller
public class HospitalController {

	
	@Resource
	private CommonService commonService;
	
	@Resource 
	private HospitalService hospitalService;

	@RequestMapping("/hospitalPage")
	public ModelAndView doctorPage(ModelAndView modelAndView) {
		List<BlueHospital> type0 = commonService.findHospitalByType(0);
		List<BlueHospital> type1 = commonService.findHospitalByType(1);
		List<BlueHospital> type11 = commonService.findHospitalByType(11);
		List<BlueHospital> type12 = commonService.findHospitalByType(12);
		List<BlueHospital> type13 = commonService.findHospitalByType(13);
		List<BlueHospital> type14 = commonService.findHospitalByType(14);
		List<BlueHospital> type21 = commonService.findHospitalByType(21);
		List<BlueHospital> type22 = commonService.findHospitalByType(22);
		
		modelAndView.addObject("hospitalHJ0", type0);
		modelAndView.addObject("hospitalBN1", type1.size()>=1?type1.get(0):null);
		modelAndView.addObject("hospitalJSA", type21.size()>=1?type21.get(0):null);
		modelAndView.addObject("hospitalJSB", type22.size()>=1?type22.get(0):null);
		modelAndView.addObject("hospitalBN11", type11.size()>=1?type11.get(0):null);
		modelAndView.addObject("hospitalBN12", type12.size()>=1?type12.get(0):null);
		modelAndView.addObject("hospitalBN13", type13.size()>=1?type13.get(0):null);
		modelAndView.addObject("hospitalBN14", type14.size()>=1?type14.get(0):null);
		modelAndView.setViewName("/html/hospital");
		return modelAndView;
	}
	

	
}
