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
import com.common.system.entity.BlueOppointment;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.OppointmentService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Controller
@RequestMapping("oppointment")
public class OppointmentController {
	
	@Resource
	private OppointmentService oppointmentService;
	
	@Resource
	private CommonService commonService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		List<BlueDept> depts = commonService.findDept();
		modelAndView.addObject("depts", depts);
		modelAndView.setViewName("/system/admin/oppointment/list");
		return modelAndView;
	}
	
	/**
	 * 获取预约列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("page")
	public PageBean<BlueOppointment> getDoctorList(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		if (StringUtils.isEmpty(search)) {
			search = "-1";
		}
		return oppointmentService.getOppointmentList(search, start, pageSize, date);
	}
	
	 @RequestMapping(value = "add",method = RequestMethod.GET)
	    public ModelAndView add(ModelAndView modelAndView){
		 	List<BlueStaff> staffs = commonService.findStaff();
			modelAndView.addObject("staffs", staffs);
	        modelAndView.setViewName("/system/admin/oppointment/add");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "save")
	    public @ResponseBody Result<Integer> save(int staffId,String date, String payMoney){
	        return oppointmentService.addOppo(staffId, date, payMoney);
	    }
	 
	 @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
		public @ResponseBody
		Result<Integer> delete(@PathVariable Integer id) {
			Result<Integer> result = oppointmentService.deleteOppo(id);
			return result;
		}
	 
	 @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
	    public ModelAndView edit(@PathVariable Integer id,ModelAndView modelAndView){
		 	Result<BlueOppointment> result = oppointmentService.findOppo(id);
	        modelAndView.addObject("bean",result.getData());
	        modelAndView.setViewName("/system/admin/oppointment/edit");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "update",method = RequestMethod.POST)
	    public @ResponseBody Result<Integer> update(String date, int sid){	       
	        return oppointmentService.updateOppo(date, sid);
	    }

}
