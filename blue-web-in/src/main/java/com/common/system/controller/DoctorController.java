/**
 * 
 */
package com.common.system.controller;


import java.util.List;

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

import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.DoctorService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Controller
@RequestMapping("doctor")
public class DoctorController {
	
	@Resource
	private DoctorService doctorService;
	
	@Resource
	private CommonService commonService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		List<BlueDept> depts = commonService.findDept();
		modelAndView.addObject("depts", depts);
		modelAndView.setViewName("/system/admin/doctor/list");
		return modelAndView;
	}
	
	/**
	 * 获取医生列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("page")
	public PageBean<BlueStaff> getDoctorList(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		if (StringUtils.isEmpty(search)) {
			search = "-1";
		}
		return doctorService.getDoctorList(search, start, pageSize, date);
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		Result<Integer> result = doctorService.deleteDoctor(id);
		return result;
	}
	
	 @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
	    public ModelAndView view(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueStaff> result = doctorService.findDoctor(id);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/doctor/view");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "update",method = RequestMethod.POST)
	    public @ResponseBody Result<Integer> update(String name, int deptId,int sex,String phone, int sid,String introduce,String jobNum,String address){	       
	        return doctorService.updateDoctor(name, deptId, sex, phone, 18, sid,introduce,jobNum,address);
	    }
	 
	 @RequestMapping(value = "add",method = RequestMethod.GET)
	    public ModelAndView add(ModelAndView modelAndView){
		 List<BlueDept> depts = commonService.findDept();
			modelAndView.addObject("depts", depts);
	        modelAndView.setViewName("/system/admin/doctor/add");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueStaff> result = doctorService.findDoctor(id);
	        List<BlueDept> depts = commonService.findDept();
			modelAndView.addObject("depts", depts);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/doctor/edit");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "save")
	    public @ResponseBody Result<Integer> save(String jobNum,String name, int deptId,int sex,String phone,String introduce,String address,String callFee,String positionName,@RequestParam("fileName") MultipartFile file){
	        return doctorService.addDoctor(jobNum, name, deptId, sex, phone, introduce,address, callFee, positionName,file);
	    }

}
