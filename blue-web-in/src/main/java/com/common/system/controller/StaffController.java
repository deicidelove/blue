/**
 * 
 */
package com.common.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.system.entity.BlueStaff;
import com.common.system.service.StaffService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("/staff")
public class StaffController {
	@Resource
	private StaffService staffService;

	@RequestMapping("/findStaff")
	public PageBean<BlueStaff> findStaff(int startPage, int limitLength,
			String name, int deptId, String diseaseNmae) {
		return staffService.findStaff(startPage, limitLength, name, deptId,
				diseaseNmae);
	}

	@RequestMapping("/findBySid")
	public Result<BlueStaff> findBySid(int sid) {
		return staffService.findBySid(sid);
	}

	@RequestMapping("/deleteStaff")
	public Result<String> deleteStaff(int sid) {
		return staffService.deleteStaff(sid);

	}

	@RequestMapping("/updateStaff")
	public Result<String> updateStaff(String info) {
		return staffService.updateStaff(info);
	}

	@RequestMapping("/addStaff")
	public Result<String> addStaff(String info) {
		return staffService.addStaff(info);
	}

}
