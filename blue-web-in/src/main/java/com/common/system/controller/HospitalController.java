/**
 * 
 */
package com.common.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueHospital;
import com.common.system.service.HospitalService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping(value = "hospital")
public class HospitalController {

	@Resource
	private HospitalService hospitalService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/hospital/list");
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "page")
	public PageBean<BlueHospital> findBlueHospitals(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		if (StringUtils.isEmpty(search)) {
			search = "-1";
		}
		return hospitalService.findBlueHospitals(Integer.valueOf(search),
				start, pageSize);
	}

	@RequestMapping("/deleteHospital")
	public Result<String> deleteHospital(int sid) {
		return hospitalService.deleteHospital(sid);
	}

	@RequestMapping("/findHospital")
	public Result<BlueHospital> findHospital(int sid) {
		return hospitalService.findHospital(sid);
	}

	@RequestMapping("/updateHospital")
	public Result<String> updateHospital(int type, int sid, String context,
			String title) {
		return hospitalService.updateHospital(type, sid, context, title);
	}

	@RequestMapping("/addHospital")
	public Result<String> addHospital(int type, String context, String title) {
		return hospitalService.addHospital(type, context, title);
	}
}
