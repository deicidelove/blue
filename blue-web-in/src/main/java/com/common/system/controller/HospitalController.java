/**
 * 
 */
package com.common.system.controller;

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
		return hospitalService.findBlueHospitals(date,Integer.valueOf(search),
				start, pageSize);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		return hospitalService.deleteHospital(id);
	}
	
	 @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
	    public ModelAndView view(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueHospital> result = hospitalService.findHospital(id);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/hospital/view");
	        return modelAndView;
	    }

	 @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
		public ModelAndView edit(@PathVariable Integer id, ModelAndView modelAndView) {
			Result<BlueHospital> result = hospitalService.findHospital(id);
			modelAndView.addObject("bean", result.getData());
			modelAndView.addObject("url", "");
			modelAndView.setViewName("/system/admin/hospital/edit");
			return modelAndView;
		}

		@RequestMapping(value = "update", method = RequestMethod.POST)
		public @ResponseBody
		Result<Integer> updateHospital(int type, int sid, String context,
			@RequestParam("fileName") MultipartFile file,String title) {
		return hospitalService.updateHospital(type, sid, context, title,file);
	}
		@RequestMapping(value = "add", method = RequestMethod.GET)
		public ModelAndView add(ModelAndView modelAndView) {
			modelAndView.setViewName("/system/admin/hospital/add");
			return modelAndView;
		}
		@RequestMapping(value = "save")
		public @ResponseBody Result<Integer> addHospital(int type, String context, @RequestParam("fileName") MultipartFile file,String title) {
		return hospitalService.addHospital(type, context, title,file);
	}
}
