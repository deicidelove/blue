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
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueDoctorSchedule;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.DoctorSchedulEService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("doctorSchedule")
public class DoctorScheduleController {

	@Resource
	private DoctorSchedulEService doctorSchedulEService;

	@Resource
	private CommonService commonService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		List<BlueDept> depts = commonService.findDept();
		modelAndView.addObject("depts", depts);
		modelAndView.setViewName("/system/admin/doctorSchedule/list");
		return modelAndView;
	}

	/**
	 * 获取医生排班列表(某天按科室查找)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("page")
	public PageBean<BlueDoctorSchedule> getDoctorList(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "DoctorSchedule_tab_length", required = false)String DoctorSchedule_tab_length) {
		if (StringUtils.isEmpty(search)) {
			search = "-1";
		}
		return doctorSchedulEService.getDoctorList(start, pageSize, date,
				search);
	}

	/**
	 * 删除某个排班
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Result<Integer> delete(@PathVariable Integer id) {
		Result<Integer> result = doctorSchedulEService.delete(id);
		return result;
	}

	/**
	 * 添加页面数据渲染
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/doctorSchedule/add");
		List<BlueDept> depts = commonService.findDept();
		List<BlueStaff> staffs = commonService.findStaff();
		modelAndView.addObject("depts", depts);
		modelAndView.addObject("staffs", staffs);
		return modelAndView;
	}
	
	/**
	 * 保存
	 * @param date
	 * @param count
	 * @param staffId
	 * @param shiftTime
	 * @return
	 */
	@RequestMapping(value = "save")
	public @ResponseBody
	Result<Integer> save(String date, String count, int staffId,
			String shiftTime) {
		return doctorSchedulEService.save(date, count, staffId, shiftTime);
	}
	
	/**
	 * 编辑
	 * @param id
	 * @param modelAndView
	 * @return
	 */
	 @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
	        Result<BlueDoctorSchedule> result = doctorSchedulEService.findBySid(id);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/doctorSchedule/edit");
	        return modelAndView;
	    }
	 /**
	  * 更新
	  * @param name
	  * @param deptId
	  * @param sex
	  * @param phone
	  * @param sid
	  * @param introduce
	  * @return
	  */
	 @RequestMapping(value = "update",method = RequestMethod.POST)
	    public @ResponseBody Result<Integer> update(String date, String count,
				String shiftTime,int id){	       
	        return doctorSchedulEService.update(date, count, 0, shiftTime, id);
	    }

}
