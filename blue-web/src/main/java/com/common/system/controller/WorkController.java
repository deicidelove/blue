/**
 * 
 */
package com.common.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.World;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.system.entity.BlueDept;
import com.common.system.entity.BlueNeedWork;
import com.common.system.entity.BlueStaff;
import com.common.system.service.CommonService;
import com.common.system.service.NeedWorkService;
import com.common.system.util.Result;

/**
 * @author amkong
 *
 */
@Controller
public class WorkController {
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private NeedWorkService needWorkService;
	
	@RequestMapping("/workNeedPage")
	public ModelAndView workNeedPage(ModelAndView modelAndView) {

		List<BlueNeedWork> needWorks = commonService.findBlueNeedWorks();
		modelAndView.addObject("needWorks", needWorks);
		modelAndView.setViewName("/html/addOur");
		return modelAndView;
	}

	@RequestMapping(value = "workNeedDetial/{sid}", method = RequestMethod.GET)
	public ModelAndView workNeedDetialById(ModelAndView modelAndView,
			@PathVariable Integer sid) {
		Result<BlueNeedWork> result = needWorkService.findBySid(sid);
        modelAndView.addObject("work",result.getData());
        modelAndView.setViewName("/html/addOurDetail");
        return modelAndView;

	}
	
	@RequestMapping(value = "/addOurNowPage", method = RequestMethod.GET)
	public ModelAndView workNeedDetialById(ModelAndView modelAndView
			) {
        modelAndView.setViewName("/html/addOurNow");
        return modelAndView;
	}
	
	@RequestMapping(value = "submitWorkWant")
	public @ResponseBody
	Result<Integer> save(String addName, String phone, String educattion,String wantJob,
			String wageWant,String describe) {
		return needWorkService.saveAddWork(addName, phone, educattion, wantJob, wageWant, describe);
	}
	
}
