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

import com.common.system.entity.BlueSource;
import com.common.system.service.SourceService;
import com.common.system.util.PageBean;
import com.common.system.util.Result;

/**
 * @author amkong
 * 
 */
@Controller
@RequestMapping("source")
public class SourceController {

	@Resource
	private SourceService sourceService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView) {
		modelAndView.setViewName("/system/admin/source/list");
		return modelAndView;
	}

	@RequestMapping("/findSourceList")
	public PageBean<BlueSource> findSourceList(int deptId, String jobNum,
			String password, int startPage, int limit) {
		return sourceService.findSourceList(deptId, jobNum, password,
				startPage, limit);
	}

	@ResponseBody
	@RequestMapping(value = "page")
	public PageBean<BlueSource> findSourceByDept(
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "search", required = false) String search) {
		if(StringUtils.isEmpty(search)){
			search = "2";
		}
		return sourceService.findSourceByDept(Integer.valueOf(search), start,
				pageSize);
	}

	@RequestMapping("/deleteSource")
	public Result<String> deleteSource(int sid) {
		return sourceService.deleteSource(sid);
	}

	@RequestMapping("/addSource")
	public Result<String> addSource(int deptId, int positionId, String context,
			String title) {
		return sourceService.addSource(deptId, positionId, context, title);
	}

	@RequestMapping("/updateSource")
	public Result<String> updateSource(int sid, int deptId, int positionId,
			String context, String title) {
		return sourceService.updateSource(sid, deptId, positionId, context,
				title);
	}

	@RequestMapping("/findSource")
	public Result<BlueSource> findSource(int sid) {
		return sourceService.findSource(sid);
	}

}
