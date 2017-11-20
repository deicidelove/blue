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
import com.common.system.entity.BlueDeptDoctorPic;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.DoctorDeptPicService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Controller
@RequestMapping("doctorDept")
public class DoctorDeptPicController {
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private DoctorDeptPicService doctorDeptPicService;
	
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		List<BlueDept> depts = commonService.findDept();
		modelAndView.addObject("depts", depts);
		modelAndView.setViewName("/system/admin/doctorDeptPic/list");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("page")
	public PageBean<BlueDeptDoctorPic> getDoctorList(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		if (StringUtils.isEmpty(search)) {
			search = "-1";
		}
		return doctorDeptPicService.findPics(search, start, pageSize, null);
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		Result<Integer> result = doctorDeptPicService.deletePic(id);
		return result;
	}
	
	 
	 @RequestMapping(value = "add",method = RequestMethod.GET)
	    public ModelAndView add(ModelAndView modelAndView){
		 List<BlueDept> depts = commonService.findDept();
			modelAndView.addObject("depts", depts);
	        modelAndView.setViewName("/system/admin/doctorDeptPic/add");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "save")
	    public @ResponseBody Result<Integer> save(int deptId,int type,@RequestParam("fileName") MultipartFile file){
	        return doctorDeptPicService.addDoctorDeptPic(deptId,type, file);
	    }
	 
	 
	 @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueDeptDoctorPic> result = doctorDeptPicService.findDeptPic(id);
	        List<BlueDept> depts = commonService.findDept();
			modelAndView.addObject("depts", depts);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/doctorDeptPic/edit");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "update",method = RequestMethod.POST)
	    public @ResponseBody Result<Integer> update(@RequestParam("fileName") MultipartFile file,int type, int deptId,int sid){	       
	        return doctorDeptPicService.updateDoctorDeptPic(sid, deptId,type, file);
	    }
	
}
